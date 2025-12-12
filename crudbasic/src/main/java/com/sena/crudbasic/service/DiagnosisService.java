package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.Diagnosis;
import com.sena.crudbasic.dto.DiagnosisDto;

public interface DiagnosisService {

    public List<Diagnosis> findAll();
    public Diagnosis findById(int id);
    public List<Diagnosis> filterByCode(String code);
    public String save(DiagnosisDto d);
    public String delete(int id);
}