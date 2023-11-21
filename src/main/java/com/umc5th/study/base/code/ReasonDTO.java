package com.umc5th.study.base.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReasonDTO {

    private final HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final String code;
    private final String message;
}
