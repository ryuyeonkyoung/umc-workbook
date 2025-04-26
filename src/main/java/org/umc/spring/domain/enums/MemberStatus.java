package org.umc.spring.domain.enums;

public enum MemberStatus {
    ACTIVE,
    INACTIVE, // 비활성화 (Soft Delete)
    SUSPENDED,// 정지된 유저
}