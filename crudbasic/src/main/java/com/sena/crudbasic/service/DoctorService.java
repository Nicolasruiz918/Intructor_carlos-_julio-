package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.Doctor;
import com.sena.crudbasic.dto.DoctorDto;

public interface DoctorService {


    public List<Doctor> findAll();
    public Doctor findById(int id);
    public List<Doctor> filterBySpecialty(String specialty);
    public String save(DoctorDto d);
    public String delete(int id);
}