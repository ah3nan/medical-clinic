package com.ah3nan.medicalclinic.persistance.repository;

import com.ah3nan.medicalclinic.persistance.entity.Appointment;
import com.ah3nan.medicalclinic.persistance.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findAllByPatient(Patient patient, Pageable pageable);
    Page<Appointment> findAllByPatientFirstNameContaining(String patientFirstName, Pageable pageable);
    Page<Appointment> findByAppointmentDateBetween(LocalDate dateFrom, LocalDate dateTo, Pageable pageable);

}
