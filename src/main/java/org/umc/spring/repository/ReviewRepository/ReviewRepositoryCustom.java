package org.umc.spring.repository.ReviewRepository;

import com.querydsl.core.Tuple;
import java.util.List;

public interface ReviewRepositoryCustom {
    List<Tuple> findReviewsByStoreId(Long storeId, int offset, int limit);
}