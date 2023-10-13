package com.grp7.projectC.errorhandlers;

import com.grp7.projectC.customresponses.APIResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleNotFound(CustomNotFoundException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exc.getMessage());

        APIResponse<Map<String, String>> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDetails(errors);
        response.setMessage("The requested resource is not found");
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleInsufficientStock(InsufficientStockException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exc.getMessage());

        APIResponse<Map<String, String>> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setDetails(errors);
        response.setMessage("The order exceeds the available stock");
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongQuantityException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleWrongQuantity(WrongQuantityException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exc.getMessage());

        APIResponse<Map<String, String>> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setDetails(errors);
        response.setMessage("Order quantity error");
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exc.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });


        APIResponse<Map<String,String>> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.BAD_REQUEST.value());
        responseError.setMessage("There are validation errors");
        responseError.setDetails(errors);
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleConstraintViolationErrors(ConstraintViolationException exc, WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        errors.put(exc.getConstraintName(), "Validation error for: " + exc.getConstraintName());


        APIResponse<Map<String,String>> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.BAD_REQUEST.value());
        responseError.setMessage("There are validation errors");
        responseError.setDetails(errors);
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<APIResponse<String>> handleNoHandlerErrors(NoHandlerFoundException exc, WebRequest request) {

        APIResponse<String> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.NOT_FOUND.value());
        responseError.setMessage("Endpoint not found");
        responseError.setDetails(exc.getMessage());
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> handleInternalServerError(Exception exc, WebRequest request) {

        APIResponse<String> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.setMessage("Internal Server Error");
        responseError.setDetails(exc.getMessage());
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<APIResponse<String>> handleMethodNotSupportedError(HttpRequestMethodNotSupportedException exc, WebRequest request) {

        APIResponse<String> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        responseError.setMessage("Handle method error");
        responseError.setDetails(exc.getMessage());
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.METHOD_NOT_ALLOWED);
    }

}

