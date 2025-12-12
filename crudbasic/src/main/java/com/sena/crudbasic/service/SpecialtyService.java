package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.Specialty;
import com.sena.crudbasic.dto.SpecialtyDto;

public interface SpecialtyService {

    public List<Specialty> findAll();
    public Specialty findById(int id);
    public List<Specialty> filterByName(String name);
    public String save(SpecialtyDto s);
    public String delete(int id);
}