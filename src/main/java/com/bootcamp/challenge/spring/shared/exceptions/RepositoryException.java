package com.bootcamp.challenge.spring.shared.exceptions;

public class RepositoryException extends RuntimeException {
        public RepositoryException(String message) {
            super(message);
        }
        public RepositoryException(Throwable cause) {
            super(cause);
        }
        public RepositoryException(RuntimeException e) {
            super(e);
        }
}
