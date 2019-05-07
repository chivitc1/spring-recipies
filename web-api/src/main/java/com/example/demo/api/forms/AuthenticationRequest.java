package com.example.demo.api.forms;

import com.example.demo.api.exceptions.MissingParamException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter @Setter @AllArgsConstructor
public class AuthenticationRequest {
    private Optional<String> username;
    private Optional<String> password;

    public static void checkMissingParam(AuthenticationRequest authenticationRequest) {
        if (!authenticationRequest.getUsername().isPresent()) {
            throw new MissingParamException("username");
        }
        if (!authenticationRequest.getPassword().isPresent()) {
            throw new MissingParamException("password");
        }
    }
}
