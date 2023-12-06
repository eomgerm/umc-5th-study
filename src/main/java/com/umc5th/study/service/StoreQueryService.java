package com.umc5th.study.service;

import com.umc5th.study.domain.Mission;
import com.umc5th.study.domain.Review;
import org.springframework.data.domain.Page;

public interface StoreQueryService {

    Boolean isRegionExists(String regionName);

    Boolean isStoreExists(Long storeId);

    Page<Review> getReviewList(Long storeId, Integer page);

    Page<Mission> getMissionList(Long storeId, Integer page);
}
