package com.ducvu.review_service.exception;


import com.ducvu.review_service.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleGenericException(Exception exception) {
        log.error("Exception: ", exception);
        ApiResponse response = new ApiResponse();
        response.setCode(9999);
        response.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
