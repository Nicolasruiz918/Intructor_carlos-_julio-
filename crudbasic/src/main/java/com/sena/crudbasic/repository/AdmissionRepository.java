
package com.sena.crudbasic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.crudbasic.Model.Admission;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {

    List<Admission> findByIsActiveTrue();

    @Query(""
            + "SELECT "
            + "a "
            + "FROM "
            + "Admission a "
            + "WHERE "
            + "a.dischargeDate IS NULL "
            + "AND a.isActive = true")
    public List<Admission> findActiveAdmissions();
}