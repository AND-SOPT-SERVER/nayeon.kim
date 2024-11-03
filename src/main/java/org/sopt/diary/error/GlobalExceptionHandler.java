package org.sopt.diary.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<Map<String, Object>> handleTooManyRequests(TooManyRequestsException e) {
        long remainingTime = e.getRemainingTimeSenconds();
        long minutes = remainingTime / 60;
        long seconds = remainingTime % 60;
        Map<String, Object> body = new HashMap<>();
        body.put("status", 429);
        body.put("error", "Too many requests");
        body.put("message", minutes+"분 "+seconds+"초 후 다시 시도하세요.");
        return new ResponseEntity<>(body, HttpStatus.TOO_MANY_REQUESTS);
    }
    @ExceptionHandler(ExceedCharacterLimitException.class)
    public ResponseEntity<Map<String, Object>> handleExceedCharacterLimit(ExceedCharacterLimitException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("error", "Bad Request");
        body.put("message", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
