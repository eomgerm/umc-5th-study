package com.umc5th.study.web.controller;

import com.umc5th.study.base.BaseResponse;
import com.umc5th.study.converter.ReviewConverter;
import com.umc5th.study.domain.Review;
import com.umc5th.study.service.UserQueryService;
import com.umc5th.study.web.dto.response.ReviewResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "사용자 관련 API")
public class UserRestController {

    private final UserQueryService userQueryService;

    @GetMapping("/me/reviews")
    @Operation(summary = "내가 작성한 글 목록 API", description = "현재 로그인한 유저가 작성한 글을 조회합니다.")
    public ResponseEntity<BaseResponse<ReviewResponseDto.ReviewPreviewListDto>> getMyReviews(
        @RequestParam(name = "page", defaultValue = "1") @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.") Integer page) {

        Page<Review> reviewPage = userQueryService.getMyReview(1L, page);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponse.onSuccess(ReviewConverter.toReviewPreviewListDto(reviewPage)));
    }
}
