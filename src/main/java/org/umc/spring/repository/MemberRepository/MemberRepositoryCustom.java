package org.umc.spring.repository.MemberRepository;

import org.umc.spring.domain.enums.MemberMissionStatus;
import org.umc.spring.dto.MissionResponseDto;
import org.umc.spring.dto.MemberProfileResponseDto;

import java.util.List;

public interface MemberRepositoryCustom {
    MemberProfileResponseDto getMemberById(Long memberId);
    List<MissionResponseDto> getCompletedMissionsWithCursor(Long memberId, Long lastMissionId, MemberMissionStatus memberMissionStatus);
}