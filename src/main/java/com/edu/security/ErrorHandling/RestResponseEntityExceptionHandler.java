package com.edu.security.ErrorHandling;

import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { MalformedJwtException.class, NullPointerException.class,
            MethodArgumentNotValidException.class})
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

    @ExceptionHandler(value =
            {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected Map<String, Object> handleOther(Exception ex) {
        return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }




    private Map<String, Object> getErrorResponse(HttpStatus status, Exception ex) {
        String exMessage = ex.getMessage() == null || ex.getMessage().equals("") ?
            ex.getClass().getName() : ex.getMessage();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put(
                "status", status.value()
        );
        map.put(
                "message", exMessage
        );
        map.put(
                "exception", ex.getClass()
        );
        return map;
    }
}
