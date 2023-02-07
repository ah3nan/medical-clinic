package com.ah3nan.medicalclinic.service.mapper;

import com.ah3nan.medicalclinic.dto.CreatePatientRequestDTO;
import com.ah3nan.medicalclinic.dto.PatientDTO;
import com.ah3nan.medicalclinic.persistance.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PatientMapper {
    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    Patient toEntity(CreatePatientRequestDTO createPatientRequestDTO);

}
