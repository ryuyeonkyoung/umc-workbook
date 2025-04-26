package org.umc.spring.domain.enums;

public enum MissionStatus {
    CHALLENGING, // 현재 진행 가능한 미션
    COMPLETE // 진행할 수 없는 미션 (회사나 가게의 사정으로 인해 미션이 조기 종료된 경우 고려)
}