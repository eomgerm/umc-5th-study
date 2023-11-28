package com.umc5th.study.web.dto.request;

import com.umc5th.study.validation.annotation.ExistRegion;
import com.umc5th.study.validation.annotation.ExistStore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StoreRequestDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateStoreRequestDto {

        @NotBlank(message = "장소 이름은 비워둘 수 없습니다.")
        private String name;

        @NotBlank(message = "카테고리는 비워둘 수 없습니다.")
        private String category;

        @NotBlank(message = "주소는 비워둘 수 없습니다.")
        private String address;

        @NotBlank(message = "지역은 비워둘 수 없습니다.")
        @ExistRegion
        private String region;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateReviewRequestDto {

        @NotBlank(message = "가게 아이디는 필수입니다.")
        @ExistStore
        private Long storeId;

        @NotBlank(message = "별점은 비워둘 수 없습니다.")
        private Float rating;

        @NotBlank(message = "리뷰 내용은 비워둘 수 없습니다.")
        @Size(min = 1, max = 300, message = "내용은 300자 이내여야 합니다.")
        private String contents;
    }
}
