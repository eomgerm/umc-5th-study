package com.umc5th.study.web.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReviewRequestDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateReviewRequestDto {

        @NotNull(message = "별점은 비워둘 수 없습니다.")
        @Min(value = 0, message = "별점은 0점 이상 5점 이하 소수여야 합니다.")
        @Max(value = 5, message = "별점은 0점 이상 5점 이하 소수여야 합니다.")
        private Float rating;

        @NotBlank(message = "리뷰 내용은 비워둘 수 없습니다.")
        @Size(min = 1, max = 300, message = "내용은 300자 이내여야 합니다.")
        private String contents;
    }
}
