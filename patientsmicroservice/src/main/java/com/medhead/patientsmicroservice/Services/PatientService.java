package com.medhead.patientsmicroservice.Services;

import com.medhead.patientsmicroservice.Entities.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> findAll();
}
