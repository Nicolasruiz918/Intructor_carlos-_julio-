package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.HospitalRoom;
import com.sena.crudbasic.dto.HospitalRoomDto;

public interface HospitalRoomService {

    public List<HospitalRoom> findAll();
    public HospitalRoom findById(int id);
    public List<HospitalRoom> findAvailableRooms();
    public String save(HospitalRoomDto r);
    public String delete(int id);
}