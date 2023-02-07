package com.ah3nan.medicalclinic.service.mapper;

import com.ah3nan.medicalclinic.dto.AppointmentDTO;
import com.ah3nan.medicalclinic.dto.CreateAppointmentRequestDTO;
import com.ah3nan.medicalclinic.persistance.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AppointmentMapper {
    @Mapping(target = "patient", ignore = true)
    AppointmentDTO toDTO(Appointment appointment);
    Appointment toEntity(AppointmentDTO appointmentDTO);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "canceledReason", ignore = true)
    @Mapping(target = "canceled", ignore = true)
    Appointment toEntity(CreateAppointmentRequestDTO createAppointmentRequestDTO);

}
