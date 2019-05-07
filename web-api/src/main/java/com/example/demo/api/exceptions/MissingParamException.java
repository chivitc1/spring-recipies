package com.example.demo.api.exceptions;

import lombok.Getter;

public class MissingParamException extends RuntimeException {
    @Getter
    private final String fieldName;

    public MissingParamException(String _field) {
        super("Missing param");
        this.fieldName = _field;
    }
}
