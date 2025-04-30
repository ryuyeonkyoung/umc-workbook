package org.umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.umc.spring.domain.common.BaseEntity;
import org.umc.spring.domain.mapping.MemberAgree;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String body;

    private Boolean optional;
}