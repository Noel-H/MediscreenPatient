package com.noelh.mediscreenpatient.service;

import com.noelh.mediscreenpatient.dto.PatientDTO;
import com.noelh.mediscreenpatient.model.Patient;
import com.noelh.mediscreenpatient.proxies.MediscreenNoteProxy;
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
    private final MediscreenNoteProxy mediscreenNoteProxy;

    public PatientService(PatientRepository patientRepository, MediscreenNoteProxy mediscreenNoteProxy){
        this.patientRepository = patientRepository;
        this.mediscreenNoteProxy = mediscreenNoteProxy;
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
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id not found : " + id));
    }

    /**
     * Add a patient
     * @param patientDTO is the dto who contains the required information to add
     * @return the new patient
     */
    public Patient addPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        patient.setLastName(patientDTO.getLastName());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setSex(patientDTO.getSex());
        patient.setHomeAddress(patientDTO.getHomeAddress());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
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
        patient.setLastName(patientDTO.getLastName() == null ? patient.getLastName() : patientDTO.getLastName());
        patient.setFirstName(patientDTO.getFirstName() == null ? patient.getFirstName() : patientDTO.getFirstName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth() == null ? patient.getDateOfBirth() : patientDTO.getDateOfBirth());
        patient.setSex(patientDTO.getSex() == null ? patient.getSex() : patientDTO.getSex());
        patient.setHomeAddress(patientDTO.getHomeAddress() == null ? patient.getHomeAddress() : patientDTO.getHomeAddress());
        patient.setPhoneNumber(patientDTO.getPhoneNumber() == null ? patient.getPhoneNumber() : patientDTO.getPhoneNumber());
        return patientRepository.save(patient);
    }

    /**
     * Delete a patient
     * @param id is the id of the patient
     * @return the deleted patient
     */
    public Patient deletePatient(Long id) throws EntityNotFoundException {
        Patient patientToDelete = getPatientById(id);
        Patient patient = new Patient();
        patient.setId(patientToDelete.getId());
        patient.setLastName(patientToDelete.getLastName());
        patient.setFirstName(patientToDelete.getFirstName());
        patient.setDateOfBirth(patientToDelete.getDateOfBirth());
        patient.setSex(patientToDelete.getSex());
        patient.setHomeAddress(patientToDelete.getHomeAddress());
        patient.setPhoneNumber(patientToDelete.getPhoneNumber());
        patientRepository.deleteById(id);
        mediscreenNoteProxy.deleteNoteBeanByPatientId(patient.getId());
        return patient;
    }

    public Boolean isIdExist(Long id) {
        return patientRepository.findById(id).isPresent();
    }
}
