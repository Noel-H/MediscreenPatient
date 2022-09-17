package com.noelh.mediscreenpatient.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Note Bean DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTOBean {

    private Long PatientId;

    private String noteOfThePractitioner;
}
