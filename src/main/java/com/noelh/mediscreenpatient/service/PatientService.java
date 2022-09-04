package com.noelh.mediscreenpatient.service;

import com.noelh.mediscreenpatient.dto.PatientDTO;
import com.noelh.mediscreenpatient.model.Patient;
import com.noelh.mediscreenpatient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Patient Service
 */
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    /**
     * Find all patient
     *
     * @return a list of patient
     */
    public List<Patient> getPatientList(){
        return patientRepository.findAll();
    }

    /**
     * Get a patient by his id
     * @param id is the id of the patient
     * @return the wanted patient
     */
    public Patient getPatientById(Long id){
        if (!patientRepository.existsById(id)){
            throw new EntityNotFoundException("Id not found : " +id);
        }
        return patientRepository.getReferenceById(id);
    }

    /**
     * Add a patient
     * @param patientDTO is the dto who contains the required information to add
     * @return the new patient
     */
    public Patient addPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        patient.setLastName(patientDTO.lastName);
        patient.setFirstName(patientDTO.firstName);
        return patientRepository.save(patient);
    }

    /**
     * Update a patient
     * @param id is the id of the patient
     * @param patientDTO is the dto who contains the required information to update
     * @return the updated patient
     */
    public Patient updatePatient(Long id, PatientDTO patientDTO){
        Patient patient = getPatientById(id);
        patient.setLastName(patientDTO.lastName == null ? patient.getLastName() : patientDTO.lastName);
        patient.setFirstName(patientDTO.firstName == null ? patient.getFirstName() : patientDTO.firstName);
        return patientRepository.save(patient);
    }

    /**
     * Delete a patient
     * @param id is the id of the patient
     * @return the deleted patient
     */
    public Patient deletePatient(Long id){
        Patient patient = getPatientById(id);
        patientRepository.deleteById(id);
        return patient;
    }
}
