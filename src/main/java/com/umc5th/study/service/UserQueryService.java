package com.umc5th.study.service;

import com.umc5th.study.domain.Review;
import org.springframework.data.domain.Page;

public interface UserQueryService {

    Page<Review> getMyReview(Long userId, Integer page);
}
