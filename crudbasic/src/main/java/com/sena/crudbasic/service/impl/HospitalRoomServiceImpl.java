package com.sena.crudbasic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crudbasic.Model.HospitalRoom;
import com.sena.crudbasic.dto.HospitalRoomDto;
import com.sena.crudbasic.repository.HospitalRoomRepository;
import com.sena.crudbasic.service.HospitalRoomService;

@Service
public class HospitalRoomServiceImpl implements HospitalRoomService {

    @Autowired
    private HospitalRoomRepository repository;

    @Override
    public List<HospitalRoom> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public HospitalRoom findById(int id) {
        return repository.findById(id)
                .filter(HospitalRoom::isActive)
                .orElse(null);
    }

    @Override
    public List<HospitalRoom> findAvailableRooms() {
        return repository.findByOccupiedFalseAndIsActiveTrue();
    }

    @Override
    public String save(HospitalRoomDto r) {
        HospitalRoom room = new HospitalRoom();
        if (r.getId() != 0)
            room.setId(r.getId());
        room.setRoomNumber(r.getRoomNumber());
        room.setType(r.getType());
        room.setOccupied(r.isOccupied());
        room.setActive(true);
        repository.save(room);
        return "Habitación guardada correctamente";
    }

    @Override
    public String delete(int id) {
        HospitalRoom room = repository.findById(id).orElse(null);
        if (room != null && room.isActive()) {
            room.setActive(false);
            repository.save(room);
            return "Habitación eliminada correctamente";
        }
        return "Habitación no encontrada o ya eliminada";
    }
}