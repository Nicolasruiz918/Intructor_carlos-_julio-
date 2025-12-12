package com.sena.crudbasic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crudbasic.Model.Diagnosis;
import com.sena.crudbasic.Model.MedicalRecord;
import com.sena.crudbasic.dto.DiagnosisDto;
import com.sena.crudbasic.repository.DiagnosisRepository;
import com.sena.crudbasic.repository.MedicalRecordRepository;
import com.sena.crudbasic.service.DiagnosisService;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private DiagnosisRepository repository;
    @Autowired
    private MedicalRecordRepository recordRepo;

    @Override
    public List<Diagnosis> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Diagnosis findById(int id) {
        return repository.findById(id)
                .filter(Diagnosis::isActive)
                .orElse(null);
    }

    @Override
    public List<Diagnosis> filterByCode(String code) {
        return repository.filterByCode(code);
    }

    @Override
    public String save(DiagnosisDto d) {
        Diagnosis diagnosis = new Diagnosis();
        if (d.getId() != 0)
            diagnosis.setId(d.getId());
        diagnosis.setCode(d.getCode());
        diagnosis.setDescription(d.getDescription());

        MedicalRecord r = recordRepo.findById(d.getRecordId()).orElse(null);
        diagnosis.setRecord(r);
        diagnosis.setActive(true);

        repository.save(diagnosis);
        return "Diagnóstico guardado correctamente";
    }

    @Override
    public String delete(int id) {
        Diagnosis d = repository.findById(id).orElse(null);
        if (d != null && d.isActive()) {
            d.setActive(false);
            repository.save(d);
            return "Diagnóstico eliminado correctamente";
        }
        return "Diagnóstico no encontrado o ya eliminado";
    }
}