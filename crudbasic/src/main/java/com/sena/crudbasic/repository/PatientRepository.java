package com.sena.crudbasic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.crudbasic.Model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByIsActiveTrue();

    @Query(""
            + "SELECT "
            + "p "
            + "FROM "
            + "Patient p "
            + "WHERE "
            + "p.dni LIKE %?1% "
            + "AND p.isActive = true")
    public List<Patient> filterByDni(String dni);

    @Query(""
            + "SELECT "
            + "p "
            + "FROM "
            + "Patient p "
            + "WHERE "
            + "LOWER(p.fullName) LIKE LOWER(CONCAT('%', ?1, '%')) "
            + "AND p.isActive = true")
    public List<Patient> filterByFullName(String fullName);
}