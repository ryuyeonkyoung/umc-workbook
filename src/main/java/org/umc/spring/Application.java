package org.umc.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//		return args -> {
//			StoreQueryService storeService = context.getBean(StoreQueryService.class);
//			HomeQueryService homeService = context.getBean(HomeQueryService.class);
//			MemberQueryService memberService = context.getBean(MemberQueryService.class);
//			ReviewQueryService reviewService = context.getBean(ReviewQueryService.class);
//
//			// StoreQueryService 테스트
//			String name = "요아정";
//			Float score = 4.0f;
//			System.out.println("Testing findStoresByNameAndScore:");
//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);
//
//			// HomeQueryService 테스트
//			Long memberId = 1L;
//			Long lastMissionId = 10L; // 테스트용 마지막 미션 ID
//			System.out.println("Testing loadHomeMissions:");
//			homeService.loadHomeMissions(memberId)
//					.forEach(System.out::println);
//
//			// MemberQueryService 테스트
//			System.out.println("Testing loadMissionHistory:");
//			memberService.loadMissionHistory(memberId, lastMissionId, memberMissionStatus)
//					.forEach(System.out::println);
//
//			System.out.println("Testing loadMemberProfile:");
//			System.out.println(memberService.loadMemberProfile(memberId));
//
//			// ReviewQueryService 테스트
//			Long storeId = 1L;
//			System.out.println("Testing getStoreReviews:");
//			String result = reviewService.setStoreReviews(storeId, memberId, "너무 맛있어요~!", 5.0f) ?
//					"리뷰 등록 성공" : "리뷰 등록 실패";
//			System.out.println(result);
//		};
//	}
}
