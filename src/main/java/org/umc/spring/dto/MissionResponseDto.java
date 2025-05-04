package org.umc.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@AllArgsConstructor
public class MissionResponseDto {
    private final Long missionId;
    private final String status;
    private final LocalDateTime updatedAt;
    private final Integer minSpendMoney;
    private final Integer rewardPoints;
    private final String storeName;
//    private String cursorValue; // 커서 값
}