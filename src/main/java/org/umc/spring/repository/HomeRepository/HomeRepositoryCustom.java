package org.umc.spring.repository.HomeRepository;

import org.springframework.data.domain.Pageable;
import org.umc.spring.dto.HomeMissionResponseDto;

import java.util.List;

public interface HomeRepositoryCustom {
    List<HomeMissionResponseDto> findAvailableMissionsByRegion(Long memberId, Pageable pageable);
}