package com.umc5th.study.web.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionRequestDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateMissionRequestDto {

        @NotNull(message = "미션 기한은 필수입니다.")
        @Future(message = "미션 기한은 과거일 수 없습니다.")
        private LocalDate deadline;

        @NotNull(message = "보상 포인트는 필수입니다.")
        private Integer reward;

        @NotBlank(message = "미션 내용은 필수입니다.")
        private String contents;
    }
}
