package org.umc.spring.repository.MemberRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.umc.spring.domain.Member;
import org.umc.spring.domain.QMember;
import org.umc.spring.domain.QMission;
import org.umc.spring.domain.QStore;
import org.umc.spring.domain.enums.MemberMissionStatus;
import org.umc.spring.domain.mapping.QMemberMission;
import org.umc.spring.dto.MemberProfileResponseDto;
import org.umc.spring.dto.MissionResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public MemberProfileResponseDto getMemberById(Long memberId) {
        QMember m = QMember.member;

        Member member = queryFactory
                .selectFrom(m)
                .where(m.id.eq(memberId))
                .fetchOne();

        if (member == null) {
            throw new IllegalArgumentException("회원 없음");
        }

        return MemberProfileResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .phoneVerified(member.getPhoneVerified())
                .point(member.getPoint())
                .build();
    }

    @Override
    public List<MissionResponseDto> getCompletedMissionsWithCursor(Long memberId, Long lastMissionId, MemberMissionStatus memberMissionStatus) {
        QMission m = QMission.mission;
        QMemberMission mm = QMemberMission.memberMission;
        QStore s = QStore.store;

        // 1. 서브쿼리로 Cursor 값 조회
        var lastCursor = queryFactory
                .select(m.rewardPoints, mm.createdAt)
                .from(mm)
                .join(mm.mission, m)
                .where(mm.id.eq(lastMissionId))
                .fetchOne();

        if (lastCursor == null) {
            throw new IllegalArgumentException("lastMissionId에 해당하는 데이터가 없습니다: " + lastMissionId);
        }

        // 2. BooleanBuilder로 동적 조건 생성
        BooleanBuilder statusCondition = new BooleanBuilder();
        if (memberMissionStatus != null) {
            statusCondition.and(mm.status.eq(memberMissionStatus));
        }

        // 3. 복합키(rewardPoints, createdAt)로 커서 페이징 적용
        return queryFactory
                .select(Projections.constructor(
                        MissionResponseDto.class,
                        mm.mission.id,
                        mm.status,
                        mm.updatedAt,
                        m.minSpendMoney,
                        m.rewardPoints,
                        s.name,
                        Expressions.stringTemplate(
                                "CONCAT(LPAD({0}, 10, '0'), LPAD(UNIX_TIMESTAMP({1}), 10, '0'))",
                                m.rewardPoints, mm.createdAt
                        )
                ))
                .from(mm)
                .join(mm.mission, m)
                .join(m.store, s)
                .where(
                        mm.member.id.eq(memberId),
                        statusCondition,
                        new BooleanBuilder()
                                .or(m.rewardPoints.lt(lastCursor.get(0, Integer.class)))
                                .or(m.rewardPoints.eq(lastCursor.get(0, Integer.class))
                                        .and(mm.createdAt.lt(lastCursor.get(1, LocalDateTime.class))))
                )
                .orderBy(m.rewardPoints.desc(), mm.createdAt.desc())
                .limit(10)
                .fetch();
    }
}