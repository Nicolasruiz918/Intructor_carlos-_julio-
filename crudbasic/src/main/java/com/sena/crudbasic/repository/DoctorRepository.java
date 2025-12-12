package com.sena.crudbasic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.crudbasic.Model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    List<Doctor> findByIsActiveTrue();

    @Query(""
            + "SELECT "
            + "d "
            + "FROM "
            + "Doctor d "
            + "JOIN "
            + "d.specialty s "
            + "WHERE "
            + "LOWER(s.name) LIKE LOWER(CONCAT('%', ?1, '%')) "
            + "AND d.isActive = true")
    public List<Doctor> filterBySpecialty(String specialty);
}