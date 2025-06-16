package com.revature.schoolz.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.schoolz.utils.custom_exceptions.DuplicateUsernameException;
import com.revature.schoolz.utils.custom_exceptions.InvalidPasswordException;
import com.revature.schoolz.utils.custom_exceptions.InvalidSessionException;
import com.revature.schoolz.utils.custom_exceptions.InvalidUsernameException;
import com.revature.schoolz.utils.custom_exceptions.SignUpException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidUsernameException(InvalidUsernameException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidPasswordException(InvalidPasswordException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateUsernameException(DuplicateUsernameException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(409).body(response);
    }

    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<Map<String, Object>> handleSignUpException(SignUpException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidSessionException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidSessionException(InvalidSessionException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(401).body(response);
    }
}
