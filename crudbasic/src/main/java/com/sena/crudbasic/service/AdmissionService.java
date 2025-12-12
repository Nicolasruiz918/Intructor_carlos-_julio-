package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.Admission;
import com.sena.crudbasic.dto.AdmissionDto;

public interface AdmissionService {

    public List<Admission> findAll();
    public Admission findById(int id);
    public List<Admission> findActiveAdmissions();
    public String save(AdmissionDto a);
    public String delete(int id);
}