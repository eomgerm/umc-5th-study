package com.umc5th.study.service;

import com.umc5th.study.domain.Review;
import com.umc5th.study.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;

public interface MemberQueryService {

    Page<Review> getMyReview(Long userId, Integer page);

    Page<MemberMission> getMyMission(Long userId, Integer status, Integer page);
}
