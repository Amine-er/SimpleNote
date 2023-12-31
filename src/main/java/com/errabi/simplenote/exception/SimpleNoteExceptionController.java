package com.errabi.simplenote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;

import java.util.List;
import java.util.stream.Collectors;

import static com.errabi.simplenote.utils.SimpleNoteConst.BEAN_VALIDATION_ERROR_CODE;

@RestControllerAdvice
public class SimpleNoteExceptionController {
    @ExceptionHandler(value = {TechnicalException.class})
    public ResponseEntity<ErrorResponse> handleTechnicalException(TechnicalException technicalException) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(technicalException.getErrorCode())
                .errorDescription(technicalException.getErrorDescription())
                .httpStatus(technicalException.getHttpStatus())
                .build();

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        List<String> messages = ex.getBindingResult().getAllErrors().stream()
                .map(err -> err.unwrap(ConstraintViolation.class))
                .map(err -> String.format("'%s' %s", err.getPropertyPath(), err.getMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(BEAN_VALIDATION_ERROR_CODE)
                .errorDescription(messages.toString())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}