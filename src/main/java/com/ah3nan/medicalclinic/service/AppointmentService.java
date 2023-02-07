package com.ah3nan.medicalclinic.service;

import com.ah3nan.medicalclinic.dto.AppointmentDTO;
import com.ah3nan.medicalclinic.dto.AppointmentResponse;
import com.ah3nan.medicalclinic.dto.CreateAppointmentRequestDTO;
import com.ah3nan.medicalclinic.dto.PatientDTO;
import com.ah3nan.medicalclinic.error.exception.RecordNotFoundException;
import com.ah3nan.medicalclinic.persistance.repository.AppointmentRepository;
import com.ah3nan.medicalclinic.persistance.repository.PatientRepository;
import com.ah3nan.medicalclinic.service.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private final AppointmentMapper appointmentMapper;

    public AppointmentDTO create(CreateAppointmentRequestDTO createAppointmentDTO) {
        var appointment = appointmentMapper.toEntity(createAppointmentDTO);
        var savedAppointment = appointmentRepository.save(appointment);
        log.info("Created appointment : {}", savedAppointment);
        return appointmentMapper.toDTO(savedAppointment);
    }

    public AppointmentDTO cancel(AppointmentDTO canceledAppointment) {
        return partialUpdate(canceledAppointment).orElseThrow(() -> new RecordNotFoundException("Appointment Not Found"));
    }

    @Transactional(readOnly = true)
    public AppointmentResponse getAllByDate(LocalDate dateFrom, LocalDate dateTo, Pageable pageableRequest) {
        Pageable pageable = PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize());
        var appointments = appointmentRepository.findByAppointmentDateBetween(dateFrom, dateTo, pageable);
        var AppointmentList = appointments.getContent().stream().map(appointmentMapper::toDTO).toList();
        return new AppointmentResponse()
                .setContent(AppointmentList)
                .setPageNo(appointments.getNumber())
                .setPageSize(appointments.getSize())
                .setTotalElements(appointments.getTotalElements())
                .setTotalPages(appointments.getTotalPages())
                .setLast(appointments.isLast());
    }

    @Transactional(readOnly = true)
    public AppointmentResponse searchByPatientName(String patientName, Pageable pageableRequest) {
        Pageable pageable = PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize());
        var appointments = appointmentRepository.findAllByPatientFirstNameContaining(patientName, pageable);
        var AppointmentList = appointments.getContent().stream().map(appointmentMapper::toDTO).toList();
        return new AppointmentResponse()
                .setContent(AppointmentList)
                .setPageNo(appointments.getNumber())
                .setPageSize(appointments.getSize())
                .setTotalElements(appointments.getTotalElements())
                .setTotalPages(appointments.getTotalPages())
                .setLast(appointments.isLast());
    }

    @Transactional(readOnly = true)
    public AppointmentResponse getAllByPatient(Long patientId, Pageable pageableRequest) {
        var patient = patientRepository.findById(patientId).orElseThrow(() -> new RecordNotFoundException("Patient Not Found"));
        Pageable pageable = PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize());
        var appointments = appointmentRepository.findAllByPatient(patient, pageable);
        var AppointmentList = appointments.getContent().stream().map(appointmentMapper::toDTO).toList();
        return new AppointmentResponse()
                .setContent(AppointmentList)
                .setPageNo(appointments.getNumber())
                .setPageSize(appointments.getSize())
                .setTotalElements(appointments.getTotalElements())
                .setTotalPages(appointments.getTotalPages())
                .setLast(appointments.isLast());
    }

    public Optional<AppointmentDTO> partialUpdate(AppointmentDTO updatedAppointmentDTO) {
        return appointmentRepository
                .findById(updatedAppointmentDTO.getId())
                .map(existingAppointment -> {
                    if (updatedAppointmentDTO.getCanceled() != null) {
                        existingAppointment.setCanceled(updatedAppointmentDTO.getCanceled());
                    }
                    if (updatedAppointmentDTO.getAppointmentDate() != null) {
                        existingAppointment.setAppointmentDate(updatedAppointmentDTO.getAppointmentDate());
                    }
                    if (updatedAppointmentDTO.getAppointmentTime() != null) {
                        existingAppointment.setAppointmentTime(updatedAppointmentDTO.getAppointmentTime());
                    }
                    if (updatedAppointmentDTO.getCanceledReason() != null) {
                        existingAppointment.setCanceledReason(updatedAppointmentDTO.getCanceledReason());
                    }
                    return existingAppointment;
                })
                .map(appointmentRepository::save)
                .map(appointmentMapper::toDTO);
    }

    public void delete(AppointmentDTO deletedAppointmentDTO) {
        log.info("delete appointment : {}", deletedAppointmentDTO);

        appointmentRepository.deleteById(deletedAppointmentDTO.getId());
    }
}
