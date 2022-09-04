package com.noelh.mediscreenpatient.repository;

import com.noelh.mediscreenpatient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Patient Repository
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
}
