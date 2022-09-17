package com.noelh.mediscreenpatient.beans;

import lombok.Data;

/**
 * Note Bean
 */
@Data
public class NoteBean {

    private String id;

    private Long patientId;

    private String noteOfThePractitioner;
}
