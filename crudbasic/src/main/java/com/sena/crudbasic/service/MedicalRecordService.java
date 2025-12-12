package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.MedicalRecord;
import com.sena.crudbasic.dto.MedicalRecordDto;

public interface MedicalRecordService {

    public List<MedicalRecord> findAll();
    public MedicalRecord findById(int id);
    public List<MedicalRecord> filterByPatientName(String patientName);
    public String save(MedicalRecordDto m);
    public String delete(int id);
}