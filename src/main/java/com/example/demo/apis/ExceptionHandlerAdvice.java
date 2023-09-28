package com.example.demo.apis;

import com.example.demo.exceptions.DomainException;
import com.example.demo.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException de) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", de.getMessage());

        return ResponseEntity.status(400).body(errors);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleRecordNotFoundException(RecordNotFoundException de) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", de.getMessage());

        return ResponseEntity.status(404).body(errors);
    }
}
