package com.sena.crudbasic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crudbasic.Model.Doctor;
import com.sena.crudbasic.Model.Specialty;
import com.sena.crudbasic.dto.DoctorDto;
import com.sena.crudbasic.repository.DoctorRepository;
import com.sena.crudbasic.repository.SpecialtyRepository;
import com.sena.crudbasic.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private SpecialtyRepository specialtyRepo;

    @Override
    public List<Doctor> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Doctor findById(int id) {
        return repository.findById(id)
                .filter(Doctor::isActive)
                .orElse(null);
    }

    @Override
    public List<Doctor> filterBySpecialty(String specialty) {
        return repository.filterBySpecialty(specialty);
    }

    @Override
    public String save(DoctorDto d) {
        Doctor doctor = new Doctor();
        if (d.getId() != 0)
            doctor.setId(d.getId());
        doctor.setFullName(d.getFullName());
        doctor.setLicenseNumber(d.getLicenseNumber());
        doctor.setPhone(d.getPhone());
        if (d.getSpecialtyId() != 0) {
            Specialty s = specialtyRepo.findById(d.getSpecialtyId()).orElse(null);
            doctor.setSpecialty(s);
        }
        doctor.setActive(true);
        repository.save(doctor);
        return "Doctor guardado correctamente";
    }

    @Override
    public String delete(int id) {
        Doctor doctor = repository.findById(id).orElse(null);
        if (doctor != null && doctor.isActive()) {
            doctor.setActive(false);
            repository.save(doctor);
            return "Doctor eliminado correctamente";
        }
        return "Doctor no encontrado o ya eliminado";
    }
}