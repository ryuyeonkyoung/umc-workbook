package org.umc.spring.service.MemberService;

import org.springframework.data.domain.Slice;
import org.umc.spring.domain.enums.MemberMissionStatus;
import org.umc.spring.dto.MemberProfileResponseDto;
import org.umc.spring.dto.MissionResponseDto;

public interface MemberQueryService {
    Slice<MissionResponseDto> loadMissionHistory(Long memberId, Long lastMissionId, MemberMissionStatus memberMissionStatus);
    MemberProfileResponseDto loadMemberProfile(Long memberId);
}