package com.sena.crudbasic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crudbasic.Model.Specialty;
import com.sena.crudbasic.dto.SpecialtyDto;
import com.sena.crudbasic.repository.SpecialtyRepository;
import com.sena.crudbasic.service.SpecialtyService;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository repository;

    @Override
    public List<Specialty> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Specialty findById(int id) {
        return repository.findById(id)
                .filter(Specialty::isActive)
                .orElse(null);
    }

    @Override
    public List<Specialty> filterByName(String name) {
        return repository.filterByName(name);
    }

    @Override
    public String save(SpecialtyDto s) {
        Specialty specialty = new Specialty();
        if (s.getId() != 0)
            specialty.setId(s.getId());
        specialty.setName(s.getName());
        specialty.setActive(true);
        repository.save(specialty);
        return "Especialidad guardada correctamente";
    }

    @Override
    public String delete(int id) {
        Specialty s = repository.findById(id).orElse(null);
        if (s != null && s.isActive()) {
            s.setActive(false);
            repository.save(s);
            return "Especialidad eliminada correctamente";
        }
        return "Especialidad no encontrada o ya eliminada";
    }
}