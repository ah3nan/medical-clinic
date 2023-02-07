package com.ah3nan.medicalclinic.service;

import com.ah3nan.medicalclinic.dto.CreatePatientRequestDTO;
import com.ah3nan.medicalclinic.dto.PatientDTO;
import com.ah3nan.medicalclinic.persistance.repository.PatientRepository;
import com.ah3nan.medicalclinic.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private final PatientMapper patientMapper;

    public PatientDTO create(CreatePatientRequestDTO createPatientRequestDTO) {
        var patient = patientMapper.toEntity(createPatientRequestDTO);
        var savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }
}
