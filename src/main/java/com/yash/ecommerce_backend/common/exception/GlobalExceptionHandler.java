package com.yash.ecommerce_backend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleCategoryNotFound(
            CategoryNotFoundException exception
    ){
         Map<String,Object> response = Map.of(
                 "status", HttpStatus.NOT_FOUND.value(),
                 "message", exception.getMessage()
         );
         return ResponseEntity
                 .status(HttpStatus.NOT_FOUND)
                 .body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException exception
    ){
        String message = exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
        Map<String,Object> response = Map.of(
                "status",HttpStatus.BAD_REQUEST.value(),
                "message",message
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
