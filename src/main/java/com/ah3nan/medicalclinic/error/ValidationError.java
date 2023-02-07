package com.ah3nan.medicalclinic.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.io.Serializable;

@Getter
@Setter
public class ValidationError implements Serializable {
    private String field;
    private String message;
    public ValidationError() {

    }
    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ValidationError(FieldError fieldError) {
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }
}
