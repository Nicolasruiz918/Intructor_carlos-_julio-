package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.Patient;
import com.sena.crudbasic.dto.PatientDto;

public interface PatientService {

    public List<Patient> findAll();
    public Patient findById(int id);
    public List<Patient> filterByDni(String dni);
    public List<Patient> filterByFullName(String fullName);
    public String save(PatientDto p);
    public String delete(int id);          }