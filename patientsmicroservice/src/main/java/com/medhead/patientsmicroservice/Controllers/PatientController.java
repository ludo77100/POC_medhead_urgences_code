package com.medhead.patientsmicroservice.Controllers;

import com.medhead.patientsmicroservice.Entities.Patient;
import com.medhead.patientsmicroservice.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    //TODO FindPatientById
    //TODO FindPatientByLastNameAndFirstName
    //TODO FindPatientByIdCardNumber
    //TODO FindAllPatientWithNoAssignment

    //TODO CreatePatient (Cherche d'abord un patient existant et si n'existe pas en créé un)

    //TODO UpdatePatientGeneralInformation (Etat civil et Addresse)
    //TODO UpdatePatientCareInformation
    //TODO UpdatePatientOpenCare

    //TODO DeletePatient
}
