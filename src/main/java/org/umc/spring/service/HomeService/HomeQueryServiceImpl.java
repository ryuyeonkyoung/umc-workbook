package org.umc.spring.service.HomeService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.umc.spring.dto.HomeMissionResponseDto;
import org.umc.spring.repository.HomeRepository.HomeRepositoryCustom;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeQueryServiceImpl implements HomeQueryService {

    private final HomeRepositoryCustom homeRepository;

    @Override
    public Slice<HomeMissionResponseDto> loadHomeMissions(Long memberId) {
        Pageable pageable = PageRequest.of(0, 10);
        List<HomeMissionResponseDto> missions = homeRepository.findAvailableMissionsByRegion(memberId, pageable);

        // Slice로 변환
        boolean hasNext = missions.size() > pageable.getPageSize();
        return new SliceImpl<>(missions, pageable, hasNext);
    }
}