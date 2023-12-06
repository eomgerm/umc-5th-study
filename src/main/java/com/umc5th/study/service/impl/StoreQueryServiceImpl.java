package com.umc5th.study.service.impl;

import com.umc5th.study.base.code.status.ErrorStatus;
import com.umc5th.study.domain.Review;
import com.umc5th.study.domain.Store;
import com.umc5th.study.exception.handler.StoreException;
import com.umc5th.study.repository.RegionRepository;
import com.umc5th.study.repository.ReviewRepository;
import com.umc5th.study.repository.StoreRepository;
import com.umc5th.study.service.StoreQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Boolean isRegionExists(String regionName) {
        return regionRepository.existsByName(regionName);
    }

    @Override
    public Boolean isStoreExists(Long storeId) {
        return storeRepository.existsById(storeId);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));

        Page<Review> reviewPage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return reviewPage;
    }
}
