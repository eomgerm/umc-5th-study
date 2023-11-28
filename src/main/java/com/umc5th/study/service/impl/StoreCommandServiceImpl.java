package com.umc5th.study.service.impl;

import com.umc5th.study.base.code.status.ErrorStatus;
import com.umc5th.study.converter.StoreConverter;
import com.umc5th.study.domain.Region;
import com.umc5th.study.domain.Store;
import com.umc5th.study.exception.handler.StoreException;
import com.umc5th.study.repository.RegionRepository;
import com.umc5th.study.repository.StoreRepository;
import com.umc5th.study.service.StoreCommandService;
import com.umc5th.study.web.dto.request.StoreRequestDto.CreateStoreRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store createStore(CreateStoreRequestDto request) {
        Store newStore = StoreConverter.toStore(request);
        Region region = regionRepository.findByName(request.getRegion()).orElseThrow(() -> new StoreException(ErrorStatus.REGION_NOT_FOUND));

        newStore.setRegion(region);

        storeRepository.save(newStore);

        return newStore;
    }
}
