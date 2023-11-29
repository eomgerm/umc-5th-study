package com.umc5th.study.web.controller;

import com.umc5th.study.base.BaseResponse;
import com.umc5th.study.base.code.status.SuccessStatus;
import com.umc5th.study.converter.ReviewConverter;
import com.umc5th.study.domain.Review;
import com.umc5th.study.service.ReviewCommandService;
import com.umc5th.study.validation.annotation.ExistStore;
import com.umc5th.study.web.dto.request.ReviewRequestDto;
import com.umc5th.study.web.dto.response.ReviewResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "리뷰 관련 API")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/api/stores/{storeId}/reviews")
    @Operation(summary = "리뷰 등록 API", description = "해당 가게에 리뷰를 등록합니다.")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<BaseResponse<ReviewResponseDto.CreateReviewResponseDto>> createStoreReview(
        @ExistStore @PathVariable("storeId") Long storeId, @RequestBody @Valid ReviewRequestDto.CreateReviewRequestDto request) {

        Review newReview = reviewCommandService.createReview(storeId, request, 1L);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(BaseResponse.of(SuccessStatus.CREATED, ReviewConverter.toCreateReviewResponseDto(newReview)));
    }
}
