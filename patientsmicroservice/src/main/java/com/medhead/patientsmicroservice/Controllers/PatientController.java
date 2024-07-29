package com.medhead.patientsmicroservice.Controllers;

import com.medhead.patientsmicroservice.Entities.Patient;
import com.medhead.patientsmicroservice.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService ;

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> findAllPatient(){
        List<Patient> patientList = patientService.findAll();
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> findPatientById(@PathVariable Long patientId) {
        Optional<Patient> patient = patientService.findById(patientId) ;
        return patient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        }

    @GetMapping("/name")
    public ResponseEntity<Patient> findPatientByName(@RequestParam String firstName, @RequestParam String lastName) {
        Optional<Patient> patient = patientService.findByName(firstName, lastName) ;
        return patient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //TODO FindPatientByIdCardNumber
    //TODO FindAllPatientWithNoAssignment

    //TODO CreatePatient (Cherche d'abord un patient existant et si n'existe pas en créé un)

    //TODO UpdatePatientGeneralInformation (Etat civil et Addresse)
    //TODO UpdatePatientCareInformation
    //TODO UpdatePatientOpenCare
    //TODO AttachPatientToAnother

    //TODO DeletePatient
}
