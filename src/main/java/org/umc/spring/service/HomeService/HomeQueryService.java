package org.umc.spring.service.HomeService;

import org.springframework.data.domain.Slice;
import org.umc.spring.dto.HomeMissionResponseDto;

public interface HomeQueryService {
    Slice<HomeMissionResponseDto> loadHomeMissions(Long memberId);
}