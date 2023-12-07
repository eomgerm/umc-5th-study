package com.umc5th.study.web.controller;

import com.umc5th.study.base.BaseResponse;
import com.umc5th.study.converter.MissionConverter;
import com.umc5th.study.converter.ReviewConverter;
import com.umc5th.study.domain.Review;
import com.umc5th.study.domain.mapping.MemberMission;
import com.umc5th.study.service.MemberQueryService;
import com.umc5th.study.validation.annotation.ValidateMissionStatus;
import com.umc5th.study.validation.annotation.ValidatePage;
import com.umc5th.study.web.dto.response.MissionResponseDto;
import com.umc5th.study.web.dto.response.ReviewResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "사용자 관련 API")
public class MemberRestController {

    private final MemberQueryService memberQueryService;

    @GetMapping("/me/reviews")
    @Operation(summary = "내가 작성한 글 목록 API", description = "현재 로그인한 유저가 작성한 글을 조회합니다.")
    public ResponseEntity<BaseResponse<ReviewResponseDto.ReviewPreviewListDto>> getMyReviews(
        @RequestParam(name = "page", defaultValue = "1") @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.") Integer page) {

        Page<Review> reviewPage = memberQueryService.getMyReview(1L, page);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(BaseResponse.onSuccess(ReviewConverter.toReviewPreviewListDto(reviewPage)));
    }

    @GetMapping("/me/missions")
    @Operation(summary = "내 미션 목록 API", description = "내 미션 목록을 조회합니다. status에 따라 진행 중, 진행 완료 조회")
    public ResponseEntity<BaseResponse<MissionResponseDto.MissionPreviewListResponseDto>> getMyMissions(
        @RequestParam("status") @ValidateMissionStatus Integer status,
        @RequestParam("page") @ValidatePage Integer page
    ) {
        Page<MemberMission> memberMissionPage = memberQueryService.getMyMission(1L, status, page);

        return ResponseEntity.ok(BaseResponse.onSuccess(MissionConverter.toMissionPreviewListResponseDtoFromMemberMissions(memberMissionPage)));
    }
}
