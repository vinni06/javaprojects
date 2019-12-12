package com.mindtree.kalingahospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.kalingahospital.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
