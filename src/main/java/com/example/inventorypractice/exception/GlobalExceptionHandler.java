package com.example.inventorypractice.exception;

// 自己添加 import

import com.example.inventorypractice.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(
            BusinessException exception) {

        // 返回真实 HTTP 状态码
        return ResponseEntity.status(exception.getCode())
        // Body 使用 ApiResponse.error(...)
        .body(ApiResponse.error(exception.getCode(), exception.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException exception) {

        FieldError fieldError =
                exception.getBindingResult().getFieldError();

        String message = fieldError == null
                ? "请求参数错误"
                : fieldError.getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(400, message));
    }
}