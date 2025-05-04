package org.umc.spring.repository.HomeRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.umc.spring.domain.QMember;
import org.umc.spring.domain.QMission;
import org.umc.spring.domain.QStore;
import org.umc.spring.domain.enums.MissionStatus;
import org.umc.spring.dto.HomeMissionResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HomeRepositoryImpl implements HomeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HomeMissionResponseDto> findAvailableMissionsByRegion(Long memberId, Pageable pageable) {
        QMember m = QMember.member;
        QMission mission = QMission.mission;
        QStore s = QStore.store;

        // 회원 주소 조회
        String memberAddress = queryFactory
                .select(m.address)
                .from(m)
                .where(m.id.eq(memberId))
                .fetchOne();

        if (memberAddress == null) {
            throw new IllegalArgumentException("memberId에 해당하는 데이터가 없습니다: " + memberId);
        }

        // 미션 리스트 조회
        return queryFactory
                .select(Projections.constructor(HomeMissionResponseDto.class,
                        mission.id,
                        mission.minSpendMoney,
                        mission.rewardPoints,
                        s.name,
                        s.address
                ))
                .from(mission)
                .join(mission.store, s)
                .where(
                        mission.status.eq(MissionStatus.CHALLENGING),
                        s.address.contains(memberAddress)
                )
                .orderBy(mission.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}