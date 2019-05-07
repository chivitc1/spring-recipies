package com.example.demo.api.exceptions;

import lombok.Getter;

public class NonNullParamException extends RuntimeException {
    @Getter
    private final String fieldName;

    public NonNullParamException(String _field) {
        super("Param must not be null or empty");
        this.fieldName = _field;
    }
}
