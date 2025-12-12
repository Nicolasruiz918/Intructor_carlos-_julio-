package com.sena.crudbasic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crudbasic.Model.MedicalRecord;
import com.sena.crudbasic.Model.Patient;
import com.sena.crudbasic.Model.Doctor;
import com.sena.crudbasic.dto.MedicalRecordDto;
import com.sena.crudbasic.repository.MedicalRecordRepository;
import com.sena.crudbasic.repository.PatientRepository;
import com.sena.crudbasic.repository.DoctorRepository;
import com.sena.crudbasic.service.MedicalRecordService;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository repository;
    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private DoctorRepository doctorRepo;

    @Override
    public List<MedicalRecord> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public MedicalRecord findById(int id) {
        return repository.findById(id)
                .filter(MedicalRecord::isActive)
                .orElse(null);
    }

    @Override
    public List<MedicalRecord> filterByPatientName(String patientName) {
        return repository.filterByPatientName(patientName);
    }

    @Override
    public String save(MedicalRecordDto m) {
        MedicalRecord record = new MedicalRecord();
        if (m.getId() != 0)
            record.setId(m.getId());
        record.setRecordDate(m.getRecordDate());
        record.setSymptoms(m.getSymptoms());
        record.setObservations(m.getObservations());

        Patient p = patientRepo.findById(m.getPatientId()).orElse(null);
        Doctor d = doctorRepo.findById(m.getDoctorId()).orElse(null);

        record.setPatient(p);
        record.setDoctor(d);
        record.setActive(true);

        repository.save(record);
        return "Historia clínica guardada correctamente";
    }

    @Override
    public String delete(int id) {
        MedicalRecord m = repository.findById(id).orElse(null);
        if (m != null && m.isActive()) {
            m.setActive(false);
            repository.save(m);
            return "Historia clínica eliminada correctamente";
        }
        return "Historia clínica no encontrada o ya eliminada";
    }
}