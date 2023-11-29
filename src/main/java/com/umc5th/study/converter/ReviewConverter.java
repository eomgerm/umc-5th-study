package com.umc5th.study.converter;

import com.umc5th.study.domain.Review;
import com.umc5th.study.web.dto.request.ReviewRequestDto;
import com.umc5th.study.web.dto.response.ReviewResponseDto;

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
}
