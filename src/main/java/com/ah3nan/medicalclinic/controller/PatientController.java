package com.ah3nan.medicalclinic.controller;

import com.ah3nan.medicalclinic.dto.CreatePatientRequestDTO;
import com.ah3nan.medicalclinic.dto.PatientDTO;
import com.ah3nan.medicalclinic.service.PatientService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private final PatientService patientService;
    @Operation(summary = "Create New Patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Patient Created",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = PatientDTO.class))}),
            @ApiResponse(responseCode = "400" , description = "Validation Error",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody CreatePatientRequestDTO createPatientRequestDTO){
        var savedPatient = patientService.create(createPatientRequestDTO);

        return ResponseEntity.ok(savedPatient);
    }
}
