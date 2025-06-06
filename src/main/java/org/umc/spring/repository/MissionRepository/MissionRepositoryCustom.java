package org.umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Pageable;
import org.umc.spring.dto.mission.response.MissionResponseDTO;

import java.util.List;

public interface MissionRepositoryCustom {
    List<MissionResponseDTO> findAvailableMissionsByRegion(Long memberId, Pageable pageable);
}

