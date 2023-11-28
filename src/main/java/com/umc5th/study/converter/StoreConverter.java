package com.umc5th.study.converter;

import com.umc5th.study.domain.Store;
import com.umc5th.study.web.dto.request.StoreRequestDto;
import com.umc5th.study.web.dto.response.StoreResponseDto;

public class StoreConverter {

    public static Store toStore(StoreRequestDto.CreateStoreRequestDto request) {
        return Store.builder()
                    .name(request.getName())
                    .address(request.getAddress())
                    .category(request.getCategory())
                    .build();
    }

    public static StoreResponseDto.CreateStoreResponseDto toCreateStoreResponseDto(Store store) {
        return StoreResponseDto.CreateStoreResponseDto.builder()
                                                      .storeId(store.getStoreId())
                                                      .build();
    }
}
