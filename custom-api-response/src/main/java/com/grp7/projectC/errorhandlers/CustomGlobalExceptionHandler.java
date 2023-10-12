package com.grp7.projectC.errorhandlers;

import com.grp7.projectC.customresponses.APIResponse;
import com.grp7.projectC.customresponses.CustomErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

//    @ExceptionHandler(CustomNotFoundException.class)
//    public ResponseEntity<CustomErrorResponse> customNotFound(CustomNotFoundException exc, WebRequest request) {
//        CustomErrorResponse error = new CustomErrorResponse();
//        error.setTimestamp(LocalDateTime.now());
//        error.setError(exc.getMessage());
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setPath(request.getDescription(false));
//        error.setMessage("The requested resource was not found.");
//
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> customNotFound(CustomNotFoundException exc, WebRequest request) {
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

}

