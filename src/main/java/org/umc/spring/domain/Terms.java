package org.umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.umc.spring.domain.common.BaseEntity;
import org.umc.spring.domain.mapping.MemberAgree;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "terms", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<MemberAgree> memberAgrees = new HashSet<>();

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean optional;

    public void addMemberAgree(MemberAgree memberAgree) {
        if (memberAgree == null || this.memberAgrees.contains(memberAgree)) return;
        this.memberAgrees.add(memberAgree);
        memberAgree.setTerms(this);
    }

    public void removeMemberAgree(MemberAgree memberAgree) {
        if (memberAgree == null || !this.memberAgrees.contains(memberAgree)) return;
        this.memberAgrees.remove(memberAgree);
        memberAgree.setTerms(null);
    }
}