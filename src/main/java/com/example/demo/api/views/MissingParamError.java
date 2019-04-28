package com.example.demo.api.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class MissingParamError {
    private String error;
    private String fieldName;
}
