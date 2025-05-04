package org.umc.spring.service.ReviewService;

public interface ReviewQueryService {
    boolean setStoreReviews(Long storeId, Long memberId, String context, Float rating);
}