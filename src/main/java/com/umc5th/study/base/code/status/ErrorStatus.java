package com.umc5th.study.base.code.status;

import com.umc5th.study.base.code.BaseCode;
import com.umc5th.study.base.code.ReasonDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorStatus implements BaseCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "서버 에러"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "COMMON_401", "잘못된 요청"),
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP_001", "테스트 에러"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                        .message(message)
                        .code(code)
                        .isSuccess(false)
                        .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                        .httpStatus(httpStatus)
                        .code(code)
                        .message(message)
                        .isSuccess(false)
                        .build();
    }
}
