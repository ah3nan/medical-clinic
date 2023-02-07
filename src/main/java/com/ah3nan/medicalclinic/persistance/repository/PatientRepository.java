package com.ah3nan.medicalclinic.persistance.repository;

import com.ah3nan.medicalclinic.persistance.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
