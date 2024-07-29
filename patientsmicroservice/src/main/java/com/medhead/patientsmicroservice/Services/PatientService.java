package com.medhead.patientsmicroservice.Services;

import com.medhead.patientsmicroservice.Entities.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> findAll();

    Optional<Patient> findById(Long patientId);
}
