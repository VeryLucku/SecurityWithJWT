package com.edu.security.ErrorHandling;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.RequestDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { MalformedJwtException.class, NullPointerException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Map<String, Object> handleBadRequest(Exception ex) {
        return getErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }


    @ExceptionHandler(value
            = {AuthenticationCredentialsNotFoundException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected Map<String, Object> handleUnauthorized(Exception ex) {
        return getErrorResponse(HttpStatus.UNAUTHORIZED, ex);
    }


    private Map<String, Object> getErrorResponse(HttpStatus status, Exception ex) {
        String exMessage = ex.getMessage() == null || ex.getMessage().equals("") ?
            ex.getClass().getName() : ex.getMessage();

        return Map.of(
                "status", status.value(),
                "message", exMessage
        );
    }
}
