package com.bootcamp.challenge.spring.shared.exceptions;

public class IllegalProductAtributesException extends RuntimeException {
        public IllegalProductAtributesException(String message) {
            super(message);
        }
        public IllegalProductAtributesException(Throwable cause) {
            super(cause);
        }
        public IllegalProductAtributesException(RuntimeException e) {
            super(e);
        }
}
