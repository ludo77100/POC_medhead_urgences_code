package com.medhead.patientsmicroservice.Services.Impl;

import com.medhead.patientsmicroservice.Entities.Patient;
import com.medhead.patientsmicroservice.Repositories.PatientRepository;
import com.medhead.patientsmicroservice.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository ;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAllPatient();
    }



}
