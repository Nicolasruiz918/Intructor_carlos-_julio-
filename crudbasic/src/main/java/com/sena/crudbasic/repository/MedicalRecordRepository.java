
package com.sena.crudbasic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.crudbasic.Model.MedicalRecord;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {

    List<MedicalRecord> findByIsActiveTrue();

    @Query(""
            + "SELECT "
            + "m "
            + "FROM "
            + "MedicalRecord m "
            + "JOIN "
            + "m.patient p "
            + "WHERE "
            + "LOWER(p.fullName) LIKE LOWER(CONCAT('%', ?1, '%')) "
            + "AND m.isActive = true")
    public List<MedicalRecord> filterByPatientName(String patientName);
}