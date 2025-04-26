package org.umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import org.umc.spring.domain.Member;
import org.umc.spring.domain.Mission;
import org.umc.spring.domain.enums.MemberMissionStatus;
import org.umc.spring.domain.enums.MissionStatus;
import org.umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private MemberMissionStatus status;
}