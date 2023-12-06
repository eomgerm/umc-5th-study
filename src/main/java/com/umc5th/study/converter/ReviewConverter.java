package com.umc5th.study.converter;

import com.umc5th.study.domain.Review;
import com.umc5th.study.web.dto.request.ReviewRequestDto;
import com.umc5th.study.web.dto.response.ReviewResponseDto;
import com.umc5th.study.web.dto.response.ReviewResponseDto.ReviewPreviewDto;
import java.util.List;
import org.springframework.data.domain.Page;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDto.CreateReviewRequestDto request) {
        return Review.builder()
                     .rating(request.getRating())
                     .contents(request.getContents())
                     .build();
    }

    public static ReviewResponseDto.CreateReviewResponseDto toCreateReviewResponseDto(Review review) {
        return ReviewResponseDto.CreateReviewResponseDto.builder()
                                                        .reviewId(review.getReviewId())
                                                        .build();
    }

    public static ReviewResponseDto.ReviewPreviewDto toReviewPreviewDto(Review review) {
        return ReviewResponseDto.ReviewPreviewDto.builder()
                                                 .contents(review.getContents())
                                                 .createdAt(review.getCreatedAt())
                                                 .rating(review.getRating())
                                                 .username(review.getAuthor().getName())
                                                 .build();
    }

    public static ReviewResponseDto.ReviewPreviewListDto toReviewPreviewListDto(Page<Review> reviewPage) {
        List<ReviewPreviewDto> reviewPreviewDtoList = reviewPage.stream()
                                                                .map(ReviewConverter::toReviewPreviewDto)
                                                                .toList();

        return ReviewResponseDto.ReviewPreviewListDto.builder()
                                                     .reviewList(reviewPreviewDtoList)
                                                     .isFirst(reviewPage.isFirst())
                                                     .isLast(reviewPage.isLast())
                                                     .totalPage(reviewPage.getTotalPages())
                                                     .totalElements(reviewPage.getTotalElements())
                                                     .listSize(reviewPreviewDtoList.size())
                                                     .build();
    }
}
