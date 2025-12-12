
package com.sena.crudbasic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crudbasic.Model.HospitalRoom;

@Repository
public interface HospitalRoomRepository extends JpaRepository<HospitalRoom, Integer> {

    List<HospitalRoom> findByIsActiveTrue();

    List<HospitalRoom> findByOccupiedFalseAndIsActiveTrue();
}