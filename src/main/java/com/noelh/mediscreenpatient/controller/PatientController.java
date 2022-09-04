package com.noelh.mediscreenpatient.controller;

import com.noelh.mediscreenpatient.model.Patient;
import com.noelh.mediscreenpatient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") Long id){
        log.info("DELETE /patient/{}", id);
        try {
            return ResponseEntity.ok(patientService.deletePatient(id));
        } catch (EntityNotFoundException e) {
            log.error("DELETE /patient/{} | [ERROR] : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
