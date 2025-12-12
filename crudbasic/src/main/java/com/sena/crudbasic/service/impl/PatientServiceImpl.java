
package com.sena.crudbasic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crudbasic.Model.Patient;
import com.sena.crudbasic.dto.PatientDto;
import com.sena.crudbasic.repository.PatientRepository;
import com.sena.crudbasic.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Override
    public List<Patient> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Patient findById(int id) {
        return repository.findById(id)
                .filter(Patient::isActive)
                .orElse(null);
    }

    @Override
    public List<Patient> filterByDni(String dni) {
        return repository.filterByDni(dni);
    }

    @Override
    public List<Patient> filterByFullName(String fullName) {
        return repository.filterByFullName(fullName);
    }

    @Override
    public String save(PatientDto p) {
        Patient patient = new Patient();
        if (p.getId() != 0) {
            patient = repository.findById(p.getId()).orElse(new Patient());
        }
        patient.setDni(p.getDni());
        patient.setFullName(p.getFullName());
        patient.setBirthDate(p.getBirthDate());
        patient.setPhone(p.getPhone());
        patient.setBloodType(p.getBloodType());
        patient.setAddress(p.getAddress());
        patient.setActive(true);
        repository.save(patient);
        return "Paciente guardado correctamente";
    }

    @Override
    public String delete(int id) {
        return repository.findById(id)
                .filter(Patient::isActive)
                .map(p -> {
                    p.setActive(false);
                    repository.save(p);
                    return "Paciente eliminado correctamente";
                })
                .orElse("Paciente no encontrado o ya eliminado");
    }
}