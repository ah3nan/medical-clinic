package com.ah3nan.medicalclinic.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequestDTO {
    @NotEmpty
    @Size(min = 4,max = 30)
    private String username;
    @NotEmpty
    @Size(min = 2,max = 50)
    private String password;
    @NotEmpty
    @Size(min = 2,max = 50)
    private String passwordRepeat;
    @NotEmpty
    @Size(min = 3,max = 50)
    private String firstName;
    @NotEmpty
    @Size(min = 3,max = 50)
    private String lastName;
    @NotEmpty
    @Size(min = 8,max = 14)
    private String phoneNumber;

}
