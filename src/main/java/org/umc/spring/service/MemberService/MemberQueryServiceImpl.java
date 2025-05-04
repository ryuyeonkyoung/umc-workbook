package org.umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.umc.spring.domain.enums.MemberMissionStatus;
import org.umc.spring.dto.MemberProfileResponseDto;
import org.umc.spring.dto.MissionResponseDto;
import org.umc.spring.repository.MemberRepository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    @Override
    public Slice<MissionResponseDto> loadMissionHistory(Long memberId, Long lastMissionId, MemberMissionStatus memberMissionStatus) {
        Pageable pageable = PageRequest.of(0, 10); // 기본 페이징 설정
        List<MissionResponseDto> missions = memberRepository.getCompletedMissionsWithCursor(memberId, lastMissionId, memberMissionStatus);

        // Slice로 변환
        boolean hasNext = missions.size() > pageable.getPageSize();
        return new SliceImpl<>(missions, pageable, hasNext);
    }

    @Override
    public MemberProfileResponseDto loadMemberProfile(Long memberId) {
        return memberRepository.getMemberById(memberId);
    }
}