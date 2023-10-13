package com.errabi.SimpleNote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
//HttpHeaders headers = new HttpHeaders();
//headers.add("Custom-Header", "test");