package com.revature.schoolz.utils.custom_exceptions;

public class SignUpException extends RuntimeException {
    public SignUpException(String message) {
        super(message);
    }
}
