package com.umc5th.study.web.controller;

import com.umc5th.study.base.BaseResponse;
import com.umc5th.study.base.code.status.SuccessStatus;
import com.umc5th.study.converter.MissionConverter;
import com.umc5th.study.converter.ReviewConverter;
import com.umc5th.study.converter.StoreConverter;
import com.umc5th.study.domain.Mission;
import com.umc5th.study.domain.Review;
import com.umc5th.study.domain.Store;
import com.umc5th.study.service.StoreCommandService;
import com.umc5th.study.service.StoreQueryService;
import com.umc5th.study.validation.annotation.ExistStore;
import com.umc5th.study.web.dto.request.StoreRequestDto;
import com.umc5th.study.web.dto.response.MissionResponseDto;
import com.umc5th.study.web.dto.response.ReviewResponseDto;
import com.umc5th.study.web.dto.response.StoreResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/stores")
@RequiredArgsConstructor
@Tag(name = "장소 관련 API")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/")
    @Operation(summary = "장소 추가 API", description = "특정 지역에 장소를 추가합니다.")
    public ResponseEntity<BaseResponse<StoreResponseDto.CreateStoreResponseDto>> createStore(
        @RequestBody @Valid StoreRequestDto.CreateStoreRequestDto request) {
        Store newStore = storeCommandService.createStore(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(BaseResponse.of(SuccessStatus.CREATED, StoreConverter.toCreateStoreResponseDto(newStore)));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "가게 리뷰 목록 조회 API", description = "가게의 리뷰 목록을 조회합니다.")
    public ResponseEntity<BaseResponse<ReviewResponseDto.ReviewPreviewListDto>> getReviewList(
        @ExistStore @PathVariable("storeId") Long storeId, @RequestParam("page") Integer page) {
        Page<Review> reviewPage = storeQueryService.getReviewList(storeId, page);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponse.onSuccess(ReviewConverter.toReviewPreviewListDto(reviewPage)));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "가게 미션 목록 조회 API", description = "가게의 미션 목록을 조회합니다.")
    public ResponseEntity<BaseResponse<MissionResponseDto.MissionPreviewListResponseDto>> getMissionList(
        @ExistStore @PathVariable("storeId") Long storeId, @RequestParam("page") @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.") Integer page) {

        Page<Mission> missionPage = storeQueryService.getMissionList(storeId, page);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponse.onSuccess(MissionConverter.toMissionPreviewListResponseDto(missionPage)));
    }
}
