package com.umc5th.study.service.impl;

import com.umc5th.study.repository.RegionRepository;
import com.umc5th.study.service.StoreQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final RegionRepository regionRepository;

    @Override
    public Boolean isRegionExists(String regionName) {
        return regionRepository.existsByName(regionName);
    }
}
