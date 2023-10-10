package com.grp7.projectC;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorResponse> customNotFound(NotFoundException exc, WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setError(exc.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setPath(request.getDescription(false));
        error.setMessage("The requested resource was not found.");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}

