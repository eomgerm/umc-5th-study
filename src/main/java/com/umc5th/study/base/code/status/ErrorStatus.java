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

    PAGE_BELOW_ZERO(HttpStatus.BAD_REQUEST, "COMMON_O01", "페이지 번호가 0보다 작습니다."),

    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_001", "해당 지역이 존재하지 않습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_002", "해당 가게가 존재하지 않습니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_001", "존재하지 않는 사용자입니다."),

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_001", "존재하지 않는 미션입니다."),
    MISSION_STATUS_INVALID(HttpStatus.BAD_REQUEST, "MISSION_002", "미션 상태는 0또는 1이어야 합니다."),
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
