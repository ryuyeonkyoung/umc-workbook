package org.umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.umc.spring.domain.common.BaseEntity;
import org.umc.spring.domain.enums.MissionStatus;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String missionSpec;

    private Integer minSpendMoney;

    @Column(nullable = false)
    private Integer rewardPoints;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false)
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'CHALLENGING'")
    private MissionStatus status;

    @PrePersist
    public void prePersist() {
        if (this.deadline == null) {
            this.deadline = LocalDate.now().plusDays(7);
        }
        if (this.status == null) {
            this.status = MissionStatus.CHALLENGING;
        }
        if (this.rewardPoints == null) {
            this.rewardPoints = 0;
        }
        if (this.address == null) {
            this.address = "미지정";
        }
    }
}