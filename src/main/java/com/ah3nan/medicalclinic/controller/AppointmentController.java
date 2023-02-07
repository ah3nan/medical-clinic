package com.ah3nan.medicalclinic.controller;

import com.ah3nan.medicalclinic.dto.AppointmentDTO;
import com.ah3nan.medicalclinic.dto.AppointmentResponse;
import com.ah3nan.medicalclinic.dto.CreateAppointmentRequestDTO;
import com.ah3nan.medicalclinic.service.AppointmentService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private final AppointmentService appointmentService;
    @Operation(summary = "Create new Appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Appointment Created",
            content = {@Content(mediaType = "application/json",schema = @Schema(implementation = AppointmentDTO.class))}),
            @ApiResponse(responseCode = "400" , description = "Validation Error",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@Valid @RequestBody CreateAppointmentRequestDTO createAppointment) {
        var savedAppointment = appointmentService.create(createAppointment);
        return  ResponseEntity.ok(savedAppointment);
    }
    @Operation(summary = "Get all today's appointments with paginated Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Appointments List paginated",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = AppointmentResponse.class))}),
    })
    @GetMapping(value = "/today")
    public ResponseEntity<AppointmentResponse> getTodayAppointments(Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getAllByDate(LocalDate.now(),LocalDate.now(),pageable));
    }
    @Operation(summary = "Filter appointments by date with paginated Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Appointments by Date paginated",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = AppointmentResponse.class))}),
    })
    @GetMapping(value = "/date")
    public ResponseEntity<AppointmentResponse> getAppointmentsByDate(
            @RequestParam("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  dateTo,
            Pageable pageable
    ) {
        return ResponseEntity.ok(appointmentService.getAllByDate(dateFrom,dateTo,pageable));
    }
    @Operation(summary = "Filter appointments by patient name with paginated Response")
    @GetMapping(value = "/patient/search")
    public ResponseEntity<AppointmentResponse> getAppointmentsByPatientName(
            @RequestParam("patientName") String patientName,
            Pageable pageable
    ) {

        return ResponseEntity.ok(appointmentService.searchByPatientName(patientName,pageable));
    }
    @Operation(summary = "Get All appointments history by patient with paginated Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Appointments list paginated",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = AppointmentResponse.class))}),
            @ApiResponse(responseCode = "404" , description = "Patient Not Found",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentsByPatient(
            @PathVariable("id") Long patientId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(appointmentService.getAllByPatient(patientId,pageable));
    }
    @Operation(summary = "Cancel and appointment and log the reason")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Appointment canceled",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = AppointmentDTO.class))}),
            @ApiResponse(responseCode = "400" , description = "Validation Error",
                    content = {@Content(mediaType = "application/json")})
    })
    @PatchMapping(value = "/cancel")
    public ResponseEntity<AppointmentDTO> cancelAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.cancel(appointmentDTO));
    }
    @Operation(summary = "Delete An appointment")
    @DeleteMapping
    public ResponseEntity<String> deleteAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.delete(appointmentDTO);
        return ResponseEntity.ok("Ok");
    }
}
