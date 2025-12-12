
package com.sena.crudbasic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.crudbasic.Model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByIsActiveTrue();

    @Query(""
            + "SELECT "
            + "a "
            + "FROM "
            + "Appointment a "
            + "WHERE "
            + "a.status LIKE %?1% "
            + "AND a.isActive = true")
    public List<Appointment> filterByStatus(String status);
}