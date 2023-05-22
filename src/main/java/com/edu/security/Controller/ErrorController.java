package com.edu.security.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({NullPointerException.class})
    public Map<String, Object> processValidationError(Exception e) {
        return Map.of("status", 400,
                "message", e.getMessage());
    }
}
