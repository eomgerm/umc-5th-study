package com.umc5th.study.web.dto.request;

import com.umc5th.study.validation.annotation.ExistRegion;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
