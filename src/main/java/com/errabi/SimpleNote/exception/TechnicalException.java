package com.errabi.SimpleNote.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalException extends RuntimeException {
    private String errorCode;
    private String errorDescription ;
}
