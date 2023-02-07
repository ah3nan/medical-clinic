package com.ah3nan.medicalclinic.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Setter
@Getter
@NoArgsConstructor
public class CreatePatientRequestDTO {
    @NotEmpty
    @Size(min = 3,max = 50)
    private String firstName;
    @NotEmpty
    @Size(min = 3,max = 50)
    private String lastName;
    @NotNull
    @Digits(integer = 3, fraction = 0)
    private Integer age;
    @NotEmpty
    private String address;
    @NotEmpty
    @Size(min = 8,max = 14)
    private String mobilePhoneNumber;
    @Size(min = 8,max = 14)
    private String homePhoneNumber;
}
