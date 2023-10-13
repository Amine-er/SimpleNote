package com.errabi.SimpleNote.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalException extends RuntimeException {
    private String errorCode;
    private String errorDescription ;
    private HttpStatus httpStatus;
}
