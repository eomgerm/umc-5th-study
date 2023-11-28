package com.umc5th.study.base.code.status;

import com.umc5th.study.base.code.BaseCode;
import com.umc5th.study.base.code.ReasonDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessStatus implements BaseCode {
    OK(HttpStatus.OK, "OK", "요청 성공"),
    CREATED(HttpStatus.CREATED, "CREATED", "요청 성공 및 리소스 생성됨"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                        .message(message)
                        .code(code)
                        .isSuccess(true)
                        .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                        .httpStatus(httpStatus)
                        .code(code)
                        .message(message)
                        .isSuccess(true)
                        .build();
    }
}
