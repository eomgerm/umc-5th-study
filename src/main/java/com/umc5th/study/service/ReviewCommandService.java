package com.umc5th.study.service;

import com.umc5th.study.domain.Review;
import com.umc5th.study.web.dto.request.ReviewRequestDto;

public interface ReviewCommandService {

    Review createReview(Long storeId, ReviewRequestDto.CreateReviewRequestDto request, Long memberId);
}
