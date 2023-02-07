package com.ah3nan.medicalclinic.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
public class AppointmentDTO {

    private Long id;
    private PatientDTO patient;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Boolean canceled;
    private String canceledReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
