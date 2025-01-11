package com.ducvu.review_service.exception;


import com.ducvu.review_service.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<?>> handleGenericException(Exception exception) {
        log.error("Exception: ", exception);
        ApiResponse<?> response = new ApiResponse<>();
        response.setCode(9999);
        response.setMessage(formatMessage(exception.getMessage()));
        return ResponseEntity.badRequest().body(response);
    }

    private String formatMessage(String message) {
        if (message == null || message.isEmpty()) {
            return "An unexpected error occurred.";
        }

        int startIndex = message.indexOf("[{");
        int endIndex = message.lastIndexOf("}]");

        if (startIndex != -1 && endIndex != -1) {
            String jsonMessage = message.substring(startIndex + 1, endIndex + 1);
            try {
                int codeStart = jsonMessage.indexOf("\"code\":");
                int codeEnd = jsonMessage.indexOf(",", codeStart);
                int messageStart = jsonMessage.indexOf("\"message\":\"", codeEnd);
                int messageEnd = jsonMessage.indexOf("\"", messageStart + 11);

                if (codeStart != -1 && codeEnd != -1 && messageStart != -1 && messageEnd != -1) {
                    String code = jsonMessage.substring(codeStart + 7, codeEnd).trim();
                    String errorMessage = jsonMessage.substring(messageStart + 11, messageEnd);
                    return errorMessage;
                }
            } catch (Exception e) {
                log.error("Failed to parse error message: ", e);
            }
        }

        return message;
    }

}
