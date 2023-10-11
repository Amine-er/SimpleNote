package com.errabi.SimpleNote.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;


@RestControllerAdvice
public class SimpleNoteExceptionController {

    @ExceptionHandler(value = {TechnicalException.class})
    //@ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleTechnicalException(TechnicalException technicalException){

        ErrorResponse errorResponse =    ErrorResponse.builder()
                                                        .errorCode(technicalException.getErrorCode())
                                                        .errorDescription(technicalException.getErrorDescription())
                                                        .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "test");

        return new ResponseEntity<>(errorResponse, headers, HttpStatus.NOT_FOUND);
    }

}
