package com.ah3nan.medicalclinic.error;


import com.ah3nan.medicalclinic.error.exception.RecordNotFoundException;
import com.ah3nan.medicalclinic.error.exception.UsernameExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception exception, WebRequest request) {

        var error = new ErrorResponse();
        error.setSuccess(false);
        error.setErrorType(ErrorType.GLOBAL_ERROR);
        error.setMessage(exception.getLocalizedMessage());
        error.setMessageDetails(Collections.singletonList(exception.getMessage()));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleRecordNotFound(RecordNotFoundException exception) {
        var error = new ErrorResponse();
        error.setSuccess(false);
        error.setErrorType(ErrorType.RECORD_NOT_FOUND);
        error.setMessage(exception.getLocalizedMessage());
        error.setMessageDetails(Collections.singletonList(exception.getMessage()));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<?> handleUsernameAlreadyExists(UsernameExistsException exception) {
        var error = new ErrorResponse();
        error.setSuccess(false);
        error.setErrorType(ErrorType.USERNAME_ALREADY_EXISTS);
        error.setMessage(exception.getLocalizedMessage());
        error.setMessageDetails(Collections.singletonList(exception.getMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var error = new ErrorResponse();
        error.setSuccess(false);
        error.setErrorType(ErrorType.VALIDATIONS_ERROR);
        var validationError =  ex.getBindingResult().getFieldErrors().stream().map(ValidationError::new).toList();
        error.setValidationErrors(validationError);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var error = new ErrorResponse();
        error.setSuccess(false);
        error.setErrorType(ErrorType.VALIDATIONS_ERROR);
        var validationError =  ex.getBindingResult().getFieldErrors().stream().map(ValidationError::new).toList();
        error.setValidationErrors(validationError);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
