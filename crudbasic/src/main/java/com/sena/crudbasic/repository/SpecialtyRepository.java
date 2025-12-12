package com.sena.crudbasic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.crudbasic.Model.Specialty;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {

    List<Specialty> findByIsActiveTrue();

    @Query(""
            + "SELECT "
            + "s "
            + "FROM "
            + "Specialty s "
            + "WHERE "
            + "LOWER(s.name) LIKE LOWER(CONCAT('%', ?1, '%')) "
            + "AND s.isActive = true")
    public List<Specialty> filterByName(String name);
}