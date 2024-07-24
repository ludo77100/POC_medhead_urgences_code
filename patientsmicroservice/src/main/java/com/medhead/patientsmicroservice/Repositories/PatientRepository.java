package com.medhead.patientsmicroservice.Repositories;

import com.medhead.patientsmicroservice.Entities.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Long, Patient> {
}
