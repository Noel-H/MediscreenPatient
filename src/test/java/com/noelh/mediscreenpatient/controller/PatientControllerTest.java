package com.noelh.mediscreenpatient.controller;

import com.noelh.mediscreenpatient.dto.PatientDTO;
import com.noelh.mediscreenpatient.model.Patient;
import com.noelh.mediscreenpatient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @Test
    public void getPatientList_Should_Call_PatientService_Once(){
        //Given
        when(patientService.getPatientList()).thenReturn(new ArrayList<>());

        //When
        patientController.getPatientList();

        //Then
        verify(patientService, times(1)).getPatientList();
    }

    @Test
    public void checkIfPatientIdExist_Should_Call_PatientService_Once(){
        //Given
        when(patientService.isIdExist(1L)).thenReturn(false);

        //When
        patientController.checkIfPatientIdExist(1L);

        //Then
        verify(patientService, times(1)).isIdExist(1L);
    }

    @Test
    public void getPatientById_Should_Return_Ok(){
        //Given
        Patient patient = new Patient();
        when(patientService.getPatientById(1L)).thenReturn(patient);

        ResponseEntity<Patient> expectedResult = new ResponseEntity<>(patient, HttpStatus.OK);
        //When
        ResponseEntity<Patient> result = patientController.getPatientById(1L);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getPatientById_Should_Return_NotFound(){
        //Given
        when(patientService.getPatientById(1L)).thenThrow(new EntityNotFoundException());

        ResponseEntity<Patient> expectedResult = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //When
        ResponseEntity<Patient> result = patientController.getPatientById(1L);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void postPatient_Should_return_Ok(){
        //Given
        PatientDTO patientDTO = new PatientDTO();
        Patient patient = new Patient();
        when(patientService.addPatient(patientDTO)).thenReturn(patient);

        ResponseEntity<Patient> expectedResult = new ResponseEntity<>(patient, HttpStatus.OK);

        //When
        ResponseEntity<Patient> result = patientController.postPatient(patientDTO);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void putPatientById_Should_Return_Ok(){
        //Given
        PatientDTO patientDTO = new PatientDTO();
        Patient patient = new Patient();
        when(patientService.updatePatient(1L, patientDTO)).thenReturn(patient);

        ResponseEntity<Patient> expectedResult = new ResponseEntity<>(patient, HttpStatus.OK);

        //When
        ResponseEntity<Patient> result = patientController.putPatientById(1L, patientDTO);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void putPatientById_Should_Return_NotFound(){
        //Given
        PatientDTO patientDTO = new PatientDTO();
        when(patientService.updatePatient(1L, patientDTO)).thenThrow(new EntityNotFoundException());

        ResponseEntity<Patient> expectedResult = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //When
        ResponseEntity<Patient> result = patientController.putPatientById(1L, patientDTO);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void deletePatientById_Should_Return_Ok(){
        //Given
        Patient patient = new Patient();
        when(patientService.deletePatient(1L)).thenReturn(patient);

        ResponseEntity<Patient> expectedResult = new ResponseEntity<>(patient, HttpStatus.OK);

        //When
        ResponseEntity<Patient> result = patientController.deletePatientById(1L);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void deletePatientById_Should_Return_NotFound(){
        //Given
        when(patientService.deletePatient(1L)).thenThrow(new EntityNotFoundException());

        ResponseEntity<Patient> expectedResult = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //When
        ResponseEntity<Patient> result = patientController.deletePatientById(1L);

        //Then
        assertThat(result).isEqualTo(expectedResult);
    }

}