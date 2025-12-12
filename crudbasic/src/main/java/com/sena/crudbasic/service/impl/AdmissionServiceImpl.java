
package com.sena.crudbasic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crudbasic.Model.Admission;
import com.sena.crudbasic.dto.AdmissionDto;
import com.sena.crudbasic.repository.AdmissionRepository;
import com.sena.crudbasic.service.AdmissionService;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    @Autowired
    private AdmissionRepository repository;

    @Override
    public List<Admission> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Admission findById(int id) {
        return repository.findById(id)
                .filter(Admission::isActive)
                .orElse(null);
    }

    @Override
    public List<Admission> findActiveAdmissions() {
        return repository.findActiveAdmissions();
    }

    @Override
    public String save(AdmissionDto a) {
        Admission admission = new Admission();
        if (a.getId() != 0) {
            admission = repository.findById(a.getId()).orElse(new Admission());
        }
        admission.setAdmissionDate(a.getAdmissionDate());
        admission.setDischargeDate(a.getDischargeDate());
        admission.setActive(true);
        repository.save(admission);
        return "Admisión guardada correctamente";
    }

    @Override
    public String delete(int id) {
        Admission admission = repository.findById(id).orElse(null);
        if (admission != null && admission.isActive()) {
            admission.setActive(false);
            repository.save(admission);
            return "Admisión eliminada correctamente";
        }
        return "Admisión no encontrada o ya eliminada";
    }
}