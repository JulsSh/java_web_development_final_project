package ru.eventify.eventify_backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.eventify.eventify_backend.dto.response.ErrorResponse;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
   public ResponseEntity<ErrorResponse> handleEmailExist(EmailAlreadyExistsException ex) {
        log.warn("Email conflict: {}", ex.getMessage());
        ErrorResponse body = new ErrorResponse(
                "EMAIL_ALREADY_EXISTS",
                ex.getMessage(),
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex){

        String fields = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn("Validation failed: {}", fields);
        ErrorResponse body = new ErrorResponse(
                "VALIDATION_FAILED",
                fields,
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAny(Exception ex){
    log.error("Unexpected error", ex);
        ErrorResponse body =new ErrorResponse(
                "INTERNAL_ERROR",
                "An unexpected error occurred",
               Instant.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
}


}
