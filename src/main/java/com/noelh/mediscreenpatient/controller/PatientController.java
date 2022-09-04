package com.noelh.mediscreenpatient.controller;

import com.noelh.mediscreenpatient.model.Patient;
import com.noelh.mediscreenpatient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("")
    public List<Patient> getPatientList(){
        log.info("GET /");
        return patientService.getPatientList();
    }
}
