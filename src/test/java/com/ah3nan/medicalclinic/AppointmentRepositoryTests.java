package com.ah3nan.medicalclinic;

import com.ah3nan.medicalclinic.persistance.entity.Appointment;
import com.ah3nan.medicalclinic.persistance.entity.Patient;
import com.ah3nan.medicalclinic.persistance.repository.AppointmentRepository;
import com.ah3nan.medicalclinic.persistance.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppointmentRepositoryTests {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientRepository patientRepository;
    @Test
    void findAllByPatient() {
        var patient = new Patient();
        patient.setFirstName( "Ahmed" );
        patient.setLastName( "Metwally" );
        patient.setAddress( "Mansoura" );
        patient.setAge( 50 );
        patient.setMobilePhoneNumber( "01265555556" );
        patient.setHomePhoneNumber( "010857555" );
        Patient patient1 = patientRepository.save(patient);
        var appointment = new Appointment();
        appointment.setPatient(patient1);
        appointment.setAppointmentDate(LocalDate.now());
        appointment.setAppointmentTime(LocalTime.now());
;        var appointment2 = new Appointment();
        appointment2.setPatient(patient1);
        appointment2.setAppointmentDate(LocalDate.now());
        appointment2.setAppointmentTime(LocalTime.now());
        appointmentRepository.saveAll(List.of(appointment,appointment2));
        Pageable pageable = PageRequest.of(1, 2);

        assertEquals(2,appointmentRepository.findAllByPatient(patient,pageable).getTotalElements());

    }

    @Test
    void findAllByPatientFirstNameContaining() {
        Pageable pageable = PageRequest.of(1, 2);

        //assertEquals(appointmentRepository.findAllByPatientFirstNameContaining("Ahmed",pageable).getTotalElements(),2);
    }

    @Test
    void findByAppointmentDateBetween() {
    }
}
