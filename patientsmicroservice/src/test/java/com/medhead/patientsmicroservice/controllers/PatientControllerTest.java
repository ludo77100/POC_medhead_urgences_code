package com.medhead.patientsmicroservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.patientsmicroservice.Controllers.PatientController;
import com.medhead.patientsmicroservice.Entities.Care;
import com.medhead.patientsmicroservice.Entities.Patient;
import com.medhead.patientsmicroservice.Entities.PostalAddress;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PatientController patientController ;

    public static List<Patient> patientList ;

    @BeforeAll
    public static void initData () {
        PostalAddress postalAddress = new PostalAddress();
        postalAddress.setPostalAddressId(1L);
        postalAddress.setStreetAddress("1 mn street");
        postalAddress.setComplementaryAddressLine("test");
        postalAddress.setCity("Paris");
        postalAddress.setPostalCode("75001");
        postalAddress.setState("StateTest");
        postalAddress.setCountry("France");

        PostalAddress postalAddress2 = new PostalAddress();
        postalAddress2.setPostalAddressId(2L);
        postalAddress2.setStreetAddress("2 mn street");
        postalAddress2.setCity("Paris");
        postalAddress2.setPostalCode("75001");
        postalAddress2.setState("StateTest2");
        postalAddress2.setCountry("France");

        Patient patient1 = new Patient();
        patient1.setPatientId(2L);
        patient1.setBirthDate(LocalDate.of(2000, 1, 1));
        patient1.setNationality("Nationality 1");
        patient1.setFirstName("John");
        patient1.setLastName("DOE");
        List<String> listId1 = List.of("7339073939", "1233083939");
        patient1.setIdCardNumberList(listId1);
        patient1.setAddress(postalAddress);

        Patient patient2 = new Patient();
        patient2.setPatientId(2L);
        patient2.setBirthDate(LocalDate.of(2000, 2, 2));
        patient2.setNationality("Nationality 2");
        patient2.setFirstName("Karen");
        patient2.setLastName("DOE");
        List<String> listId2 = List.of("7333073939", "1233789939");
        patient1.setIdCardNumberList(listId2);
        patient2.setAddress(postalAddress2);

        Care care = new Care() ;
        care.setCareId(1L);
        care.setOpenCare(false);
        care.setCareDateStart(LocalDate.of(2010,2,3));
        care.setCareDateEnd(LocalDate.of(2010, 2,4));
        care.setAssignmentHospitalId(1L);
        care.setAssignmentSpecialityId(2L);
        care.setCareLatitude(48.867279);
        care.setCareLongitude(2.781492);
        care.setPatient(patient1);

        Care care2 = new Care() ;
        care2.setCareId(2L);
        care2.setOpenCare(true);
        care2.setCareDateStart(LocalDate.of(2015,2,3));
        care2.setAssignmentHospitalId(4L);
        care2.setAssignmentSpecialityId(2L);
        care2.setCareLatitude(49.133848);
        care2.setCareLongitude(2.572024);
        care2.setPatient(patient1);

        Care care3 = new Care() ;
        care3.setCareId(3L);
        care3.setOpenCare(true);
        care3.setCareDateStart(LocalDate.of(2015,2,3));
        care3.setAssignmentHospitalId(3L);
        care3.setAssignmentSpecialityId(2L);
        care3.setCareLatitude(48.850909);
        care3.setCareLongitude(2.344342);
        care3.setPatient(patient2);

        patientList = Arrays.asList(patient1, patient2);
    }

    @Test
    public void whenGetPatientList_thenReturnsPatientList() throws Exception {


        when(patientController.findAllPatient()).thenReturn(ResponseEntity.ok(patientList));


        mockMvc.perform(get("/patient/all")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(patientList)));
    }


}
