package org.umc.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.umc.spring.domain.common.BaseEntity;
import org.umc.spring.domain.enums.CommentStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    //  대댓글 기능을 위한 자기참조 연관관계 설정

    // 부모 댓글을 참조 (null이면 최상위 댓글)
    // parent_id 외래키 생성
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // 부모 댓글 ID를 저장하는 외래키
    @JsonIgnore // 순환 참조 방지 //TODO: DTO에서도 순환 구조 주의 필요
    private Comment parent;

    // 자식 댓글 목록  (1-depth)
    // orphanRemoval 금지: 부모 댓글 삭제 시에도 자식 댓글은 남겨야 함
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @OrderBy("createdAt ASC") // 등록한 순서대로 정렬
    private List<Comment> children = new ArrayList<>();

    @Column(nullable = false, length = 500)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
    private CommentStatus status;


}