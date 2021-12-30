package com.bootcamp.challenge.spring.shared.advices;

import com.bootcamp.challenge.spring.shared.exceptions.ProductNotFoundException;
import com.bootcamp.challenge.spring.shared.exceptions.RepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class PersistanceAdviceRepository {
        @ExceptionHandler(value = RepositoryException.class)
        protected ResponseEntity<Object> handlePersistencia(RepositoryException ex, WebRequest request) {
            String bodyOfResponse = ex.getMessage();
            return ResponseEntity.badRequest().body(bodyOfResponse);
        }
        @ExceptionHandler(value = ProductNotFoundException.class)
        protected ResponseEntity<Object> handlePersistencia(ProductNotFoundException ex, WebRequest request) {
            String bodyOfResponse = ex.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
        }
}
