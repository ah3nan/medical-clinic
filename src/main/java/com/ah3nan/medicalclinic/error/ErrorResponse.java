package com.ah3nan.medicalclinic.error;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class ErrorResponse implements Serializable {
    private Boolean success = false;
    private ErrorType errorType;
    private String message;
    private List<ValidationError> validationErrors;
    private List<String> messageDetails;
    private LocalDateTime dateTime = LocalDateTime.now();


}
