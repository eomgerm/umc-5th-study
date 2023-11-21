package com.umc5th.study.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class TempResponse {

    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class TempTestDTO {

        String test;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TempExceptionDTO {

        Integer flag;
    }
}
