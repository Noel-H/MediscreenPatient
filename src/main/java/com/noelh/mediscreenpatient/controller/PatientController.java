package com.noelh.mediscreenpatient.controller;

import com.noelh.mediscreenpatient.dto.PatientDTO;
import com.noelh.mediscreenpatient.model.Patient;
import com.noelh.mediscreenpatient.service.PatientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/patient")
@Tag(name = "PatientController", description = "Api pour les opérations CRUD et plus sur les patients.")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @ApiOperation("Récupère une liste de tous les patients")
    @GetMapping("")
    public List<Patient> getPatientList(){
        log.info("GET /patient");
        return patientService.getPatientList();
    }

    @ApiOperation("Vérifie l'existence d'un patient grâce à un id donné")
    @GetMapping("/check/{id}")
    public Boolean checkIfPatientIdExist(@PathVariable("id") Long id){
        log.info("GET /patient/check/{}", id);
        return patientService.isIdExist(id);
    }

    @ApiOperation("Récupère un patient grâce à un id donné")
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id){
        log.info("GET /patient/{}", id);
        try {
            return ResponseEntity.ok(patientService.getPatientById(id));
        } catch (EntityNotFoundException e) {
            log.error("GET /patient/{} | [ERROR] : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Ajoute un nouveau patient")
    @PostMapping("")
    public ResponseEntity<Patient> postPatient(@RequestBody PatientDTO patientDTO){
        log.info("POST /patient : {} {}",patientDTO.getLastName(), patientDTO.getFirstName());
        return ResponseEntity.ok(patientService.addPatient(patientDTO));
    }

    @ApiOperation("Modifie un patient déjà existant grâce à un id donné")
    @PutMapping("/{id}")
    public ResponseEntity<Patient> putPatientById(@PathVariable("id") Long id,@RequestBody PatientDTO patientDTO){
        log.info("PUT /patient/{}", id);
        try {
            return ResponseEntity.ok(patientService.updatePatient(id, patientDTO));
        } catch (EntityNotFoundException e) {
            log.error("PUT /patient/{} | [ERROR] : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Supprime un patient grâce à un id donné")
    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatientById(@PathVariable("id") Long id){
        log.info("DELETE /patient/{}", id);
        try {
            return ResponseEntity.ok(patientService.deletePatient(id));
        } catch (EntityNotFoundException e) {
            log.error("DELETE /patient/{} | [ERROR] : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
