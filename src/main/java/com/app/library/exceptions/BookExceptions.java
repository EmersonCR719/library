package com.app.library.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BookExceptions extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public BookExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
