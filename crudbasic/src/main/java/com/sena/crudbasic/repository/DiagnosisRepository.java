
package com.sena.crudbasic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.crudbasic.Model.Diagnosis;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {

    List<Diagnosis> findByIsActiveTrue();

    @Query(""
            + "SELECT "
            + "d "
            + "FROM "
            + "Diagnosis d "
            + "WHERE "
            + "d.code LIKE %?1% "
            + "AND d.isActive = true")
    public List<Diagnosis> filterByCode(String code);
}