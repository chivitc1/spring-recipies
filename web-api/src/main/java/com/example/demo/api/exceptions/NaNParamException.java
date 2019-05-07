package com.example.demo.api.exceptions;

import lombok.Getter;

public class NaNParamException extends RuntimeException {
    @Getter
    private final String fieldName;

    public NaNParamException(String _field) {
        super("Param is not a number");
        this.fieldName = _field;
    }
}
