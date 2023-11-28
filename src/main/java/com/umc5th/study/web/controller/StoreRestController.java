package com.umc5th.study.web.controller;

import com.umc5th.study.base.BaseResponse;
import com.umc5th.study.base.code.status.SuccessStatus;
import com.umc5th.study.converter.StoreConverter;
import com.umc5th.study.domain.Store;
import com.umc5th.study.service.StoreCommandService;
import com.umc5th.study.web.dto.request.StoreRequestDto;
import com.umc5th.study.web.dto.response.StoreResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
@Tag(name = "장소 관련 API")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    @Operation(summary = "장소 추가 API", description = "특정 지역에 장소를 추가합니다.")
    public BaseResponse<StoreResponseDto.CreateStoreResponseDto> createStore(@RequestBody @Valid StoreRequestDto.CreateStoreRequestDto request) {
        Store newStore = storeCommandService.createStore(request);

        return BaseResponse.of(SuccessStatus.CREATED, StoreConverter.toCreateStoreResponseDto(newStore));
    }
}
