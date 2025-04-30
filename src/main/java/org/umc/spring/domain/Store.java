package org.umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.umc.spring.domain.common.BaseEntity;
import org.umc.spring.domain.enums.StoreStatus;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false)
    private Float score;

    @Enumerated(EnumType.STRING)
    private StoreStatus status; // 폐점 여부

    // 주 1회 정기휴무
    @Enumerated(EnumType.STRING)
    private DayOfWeek closedDay;

    // 여러 요일 휴무
    // @ElementCollection
    // private List<DayOfWeek> closedDays = new ArrayList<>();

    @Column(nullable = false)
    private LocalTime openTime;

    @Column(nullable = false)
    private LocalTime closeTime;

}