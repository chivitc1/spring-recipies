package com.example.demo.infrastructure;

import com.example.demo.api.exceptions.InvalidJwtAuthenticationException;
import com.example.demo.api.exceptions.MissingParamException;
import com.example.demo.api.exceptions.ResourceNotFoundException;
import com.example.demo.api.views.MissingParamError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, null,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { MissingParamException.class })
    protected ResponseEntity<MissingParamError> handleMissingParam(MissingParamException ex, WebRequest request) {
        return new ResponseEntity<MissingParamError>(new MissingParamError(ex.getMessage(), ex.getFieldName()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { InvalidJwtAuthenticationException.class })
    protected ResponseEntity<Object> handleInvalidToken(InvalidJwtAuthenticationException ex, WebRequest request) {
        HashMap<String, String> result = new HashMap();
        result.put("error", ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { BadCredentialsException.class })
    protected ResponseEntity<Object> handleWrongUsernameOrPassword(BadCredentialsException ex, WebRequest request) {
        HashMap<String, String> result = new HashMap();
        result.put("error", "Username or password is not correct");
        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }
}
