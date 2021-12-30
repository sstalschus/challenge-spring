package com.bootcamp.challenge.spring.shared.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }
    public ProductNotFoundException(RuntimeException e) {
        super(e);
    }
}
