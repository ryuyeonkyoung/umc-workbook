package org.umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.umc.spring.domain.Member;
import org.umc.spring.domain.Review;
import org.umc.spring.domain.Store;
import org.umc.spring.repository.MemberRepository.MemberRepository;
import org.umc.spring.repository.ReviewRepository.ReviewRepository;
import org.umc.spring.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean setStoreReviews(Long storeId, Long memberId, String context, Float rating) {
        // Store와 Member 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Store가 존재하지 않습니다: " + storeId));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Member가 존재하지 않습니다: " + memberId));

        // 리뷰 생성 및 저장
        Review review = Review.builder()
                .store(store)
                .member(member)
                .context(context)
                .rating(rating)
                .build();
        reviewRepository.save(review);

        return true;
    }
}