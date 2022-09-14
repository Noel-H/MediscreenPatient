package com.noelh.mediscreenpatient.model;

import com.noelh.mediscreenpatient.enumeration.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Patient model
 */
@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;

    private String firstName;

    private LocalDate dateOfBirth;

    private Gender sex;

    private String homeAddress;

    private String phoneNumber;
}
