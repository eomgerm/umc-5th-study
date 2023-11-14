package com.umc5th.study.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(Include.NON_NULL)
    private final T result;

    public static <T> BaseResponse<T> onFailure(String code, String message, T data) {
        return new BaseResponse<>(true, code, message, data);
    }
}
