package com.ah3nan.medicalclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PatientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String mobilePhoneNumber;
    private String homePhoneNumber;
    private List<AppointmentDTO> appointments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
