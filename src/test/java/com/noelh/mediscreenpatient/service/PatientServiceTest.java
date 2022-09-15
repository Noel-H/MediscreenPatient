package com.noelh.mediscreenpatient.service;

import com.noelh.mediscreenpatient.dto.PatientDTO;
import com.noelh.mediscreenpatient.enumeration.Gender;
import com.noelh.mediscreenpatient.model.Patient;
import com.noelh.mediscreenpatient.proxies.MediscreenNoteProxy;
import com.noelh.mediscreenpatient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private MediscreenNoteProxy mediscreenNoteProxy;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void getPatientList_Should_Call_PatientRepository_FindAll_Once(){
        //Given
        when(patientRepository.findAll()).thenReturn(new ArrayList<>());

        //When
        patientService.getPatientList();

        //Then
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void getPatientById_Should_Call_PatientRepository_FindById_Once(){
        //Given
        when(patientRepository.findById(1L)).thenReturn(Optional.of(new Patient()));

        //When
        patientService.getPatientById(1L);

        //Then
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    public void getPatientById_Should_Throw_NoSuchElementException(){
        //Given
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        //When

        //Then
        assertThrows(EntityNotFoundException.class, () -> patientService.getPatientById(1L));
    }

    @Test
    public void addPatient_Should_Call_PatientRepository_Save_Once(){
        //Given
        PatientDTO patientDTO = new PatientDTO();
        Patient patient = new Patient();
        when(patientRepository.save(patient)).thenReturn(new Patient());

        //When
        patientService.addPatient(patientDTO);

        //Then
        verify(patientRepository, times(1)).save(patient);

    }

    @Test
    public void updatePatient_Should_Call_PatientRepository_Save_Once(){
        //Given
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setLastName("Test");

        Patient patientRequested = new Patient();
        patientRequested.setId(1L);
        patientRequested.setLastName("FirstTest");

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setLastName("Test");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientRequested));
        when(patientRepository.save(patient)).thenReturn(new Patient());

        //When
        patientService.updatePatient(1L, patientDTO);

        //Then
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void updatePatient_Should_Call_PatientRepository_Save_Once_Too(){
        //Given
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("FirstNameTest");
        patientDTO.setDateOfBirth(LocalDate.of(2000,10,10));
        patientDTO.setSex(Gender.M);
        patientDTO.setHomeAddress("1 Test St");
        patientDTO.setPhoneNumber("123-456-789");

        Patient patientRequested = new Patient();
        patientRequested.setId(1L);
        patientRequested.setLastName("Test");

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setLastName("Test");
        patient.setFirstName("FirstNameTest");
        patient.setDateOfBirth(LocalDate.of(2000,10,10));
        patient.setSex(Gender.M);
        patient.setHomeAddress("1 Test St");
        patient.setPhoneNumber("123-456-789");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientRequested));
        when(patientRepository.save(patient)).thenReturn(new Patient());

        //When
        patientService.updatePatient(1L, patientDTO);

        //Then
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void deletePatient_Should_Call_PatientRepository_Delete_And_MediscreenNoteProxy_DeleteNoteBeanByPatientId_Once_Each(){
        //Given
        Patient patient = new Patient();
        patient.setId(1L);

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        doNothing().when(patientRepository).deleteById(1L);
        when(mediscreenNoteProxy.deleteNoteBeanByPatientId(1L)).thenReturn(new ArrayList<>());

        //When
        patientService.deletePatient(1L);

        //Then
        verify(patientRepository, times(1)).deleteById(1L);
        verify(mediscreenNoteProxy, times(1)).deleteNoteBeanByPatientId(1L);
    }

    @Test
    public void isIdExist_Should_Call_PatientRepository_Once_And_Return_True(){
        //Given
        when(patientRepository.findById(1L)).thenReturn(Optional.of(new Patient()));

        //When
        boolean result = patientService.isIdExist(1L);

        //Then
        verify(patientRepository, times(1)).findById(1L);
        assertThat(result).isTrue();
    }

    @Test
    public void isIdExist_Should_Call_PatientRepository_Once_And_Return_False(){
        //Given
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        //When
        boolean result = patientService.isIdExist(1L);

        //Then
        verify(patientRepository, times(1)).findById(1L);
        assertThat(result).isFalse();
    }

}