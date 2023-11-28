package com.umc5th.study.service;

import com.umc5th.study.domain.Store;
import com.umc5th.study.web.dto.request.StoreRequestDto;

public interface StoreCommandService {

    Store createStore(StoreRequestDto.CreateStoreRequestDto request);
}
