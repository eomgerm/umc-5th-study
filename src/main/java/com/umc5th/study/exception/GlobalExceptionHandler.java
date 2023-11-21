package com.umc5th.study.exception;

import com.umc5th.study.base.BaseResponse;
import com.umc5th.study.base.code.ReasonDTO;
import com.umc5th.study.base.code.status.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        String errorMessage = e.getConstraintViolations().stream()
                               .map(ConstraintViolation::getMessage)
                               .findFirst()
                               .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));

        return handleExceptionInternalConstraint(e, ErrorStatus.valueOf(errorMessage), HttpHeaders.EMPTY, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status,
        WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors()
         .forEach(fieldError -> {
             String fieldName = fieldError.getField();
             String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
             errors.merge(fieldName, errorMessage, (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
         });

        return handleExceptionInternalArgs(e, HttpHeaders.EMPTY, ErrorStatus.VALIDATION_ERROR, request, errors);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
        e.printStackTrace();

        return handleExceptionInternalFalse(
            e,
            ErrorStatus.INTERNAL_SERVER_ERROR,
            HttpHeaders.EMPTY,
            ErrorStatus.INTERNAL_SERVER_ERROR.getHttpStatus(),
            request,
            e.getMessage()
        );
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleBaseException(BaseException baseException, HttpServletRequest request) {
        ReasonDTO errorReason = baseException.getErrorReasonHttpStatus();
        return handleExceptionInternal(baseException, errorReason, null, request);
    }

    public ResponseEntity<Object> handleExceptionInternal(Exception e, ReasonDTO reason, HttpHeaders headers, HttpServletRequest request) {
        BaseResponse<Object> body = BaseResponse.onFailure(reason.getCode(), reason.getMessage(), null);

        WebRequest webRequest = new ServletWebRequest(request);
        return super.handleExceptionInternal(
            e,
            body,
            headers,
            reason.getHttpStatus(),
            webRequest
        );
    }

    private ResponseEntity<Object> handleExceptionInternalFalse(Exception e, ErrorStatus errorStatus, HttpHeaders headers, HttpStatus httpStatus,
        WebRequest request, String errorPoint) {

        BaseResponse<Object> body = BaseResponse.onFailure(errorStatus.getCode(), errorStatus.getMessage(), errorPoint);
        return super.handleExceptionInternal(
            e,
            body,
            headers,
            httpStatus,
            request
        );
    }


    private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers, ErrorStatus errorStatus, WebRequest request,
        Map<String, String> errorArgs) {
        BaseResponse<Object> body = BaseResponse.onFailure(errorStatus.getCode(), errorStatus.getMessage(), errorArgs);

        return super.handleExceptionInternal(e, body, headers, errorStatus.getHttpStatus(), request);
    }

    private ResponseEntity<Object> handleExceptionInternalConstraint(Exception e, ErrorStatus errorStatus, HttpHeaders headers, WebRequest request) {
        BaseResponse<Object> body = BaseResponse.onFailure(errorStatus.getCode(), errorStatus.getMessage(), null);

        return super.handleExceptionInternal(
            e,
            body,
            headers,
            errorStatus.getHttpStatus(),
            request
        );
    }
}
