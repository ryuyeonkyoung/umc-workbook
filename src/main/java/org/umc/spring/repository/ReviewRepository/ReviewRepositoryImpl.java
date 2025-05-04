package org.umc.spring.repository.ReviewRepository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.umc.spring.domain.QMember;
import org.umc.spring.domain.QReview;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tuple> findReviewsByStoreId(Long storeId, int offset, int limit) {
        QReview r = QReview.review;
        QMember m = QMember.member;

        return queryFactory
                .select(r.id, r.context, r.rating, r.createdAt, m.nickname)
                .from(r)
                .join(r.member, m)
                .where(r.store.id.eq(storeId))
                .orderBy(r.createdAt.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}