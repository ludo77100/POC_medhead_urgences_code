package com.medhead.patientsmicroservice.Repositories;

import com.medhead.patientsmicroservice.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);

}
