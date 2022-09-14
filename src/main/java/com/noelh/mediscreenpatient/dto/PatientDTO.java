package com.noelh.mediscreenpatient.dto;

import com.noelh.mediscreenpatient.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Patient DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    public String lastName;

    public String firstName;

    private LocalDate dateOfBirth;

    private Gender sex;

    private String homeAddress;

    private String phoneNumber;
}
