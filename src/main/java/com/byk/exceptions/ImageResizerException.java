package com.byk.exceptions;

import org.springframework.http.HttpStatus;

public class ImageResizerException extends Exception {

    private HttpStatus statusCode;

    public ImageResizerException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

}