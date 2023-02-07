package com.ah3nan.medicalclinic;

import com.ah3nan.medicalclinic.persistance.entity.Appointment;
import com.ah3nan.medicalclinic.persistance.entity.Patient;
import com.ah3nan.medicalclinic.persistance.entity.User;
import com.ah3nan.medicalclinic.service.mapper.AppointmentMapper;
import com.ah3nan.medicalclinic.service.mapper.PatientMapper;
import com.ah3nan.medicalclinic.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MapperTests {

    AppointmentMapper APPOINTMENT_INSTANCE = Mappers.getMapper( AppointmentMapper.class );
    PatientMapper PATIENT_INSTANCE = Mappers.getMapper( PatientMapper.class );
    UserMapper USER_INSTANCE = Mappers.getMapper( UserMapper.class );

    @Test
    public void AppointmentMapperTest() {
        var user = new User();
        user.setId(1L);
        user.setUsername("mido");
        user.setPassword("ahmed545345");
        user.setFirstName("Mido");
        user.setLastName("Metwally");
        user.setPhoneNumber("011166585");
        user.setRole("ROLE_ADMIN");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());


        var patient = new Patient();
        patient.setId( 1L );
        patient.setFirstName( "Ahmed" );
        patient.setLastName( "Metwally" );
        patient.setAddress( "Mansoura" );
        patient.setAge( 50 );

        patient.setMobilePhoneNumber( "01265555556" );
        patient.setHomePhoneNumber( "010857555" );
        patient.setCreatedAt(LocalDateTime.now());
        patient.setUpdatedAt( LocalDateTime.now() );
        var appointment = new Appointment();
        appointment.setId(1L);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(LocalDate.now());
        appointment.setAppointmentTime(LocalTime.now());
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt( LocalDateTime.now() );
        var appointmentDTO = APPOINTMENT_INSTANCE.toDTO(appointment);

        assertEquals(appointmentDTO.getId(), appointment.getId());
        assertEquals(appointmentDTO.getAppointmentDate(), appointment.getAppointmentDate());
        assertEquals(appointmentDTO.getAppointmentTime(), appointment.getAppointmentTime());
        assertEquals(appointmentDTO.getCreatedAt(), appointment.getCreatedAt());
        assertEquals(appointmentDTO.getUpdatedAt(), appointment.getUpdatedAt());
        assertEquals(appointmentDTO.getId(), appointment.getId());
        var patientDTO = PATIENT_INSTANCE.toDTO(patient);

        assertEquals(patientDTO.getId(), patient.getId());
        assertEquals(patientDTO.getFirstName(), patient.getFirstName());
        assertEquals(patientDTO.getLastName(), patient.getLastName());
        assertEquals(patientDTO.getAge(), patient.getAge());
        assertEquals(patientDTO.getAddress(), patient.getAddress());
        assertEquals(patientDTO.getMobilePhoneNumber(), patient.getMobilePhoneNumber());
        assertEquals(patientDTO.getHomePhoneNumber(), patient.getHomePhoneNumber());

        var userDTO = USER_INSTANCE.toDTO(user);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getFirstName(), user.getFirstName());
        assertEquals(userDTO.getLastName(), user.getLastName());
        assertEquals(userDTO.getPhoneNumber(), user.getPhoneNumber());
        assertEquals(userDTO.getRole(), user.getRole());

    }
}
