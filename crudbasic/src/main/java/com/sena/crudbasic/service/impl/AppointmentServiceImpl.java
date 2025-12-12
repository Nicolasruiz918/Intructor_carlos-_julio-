
package com.sena.crudbasic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crudbasic.Model.Appointment;
import com.sena.crudbasic.Model.Doctor;
import com.sena.crudbasic.Model.Patient;
import com.sena.crudbasic.dto.AppointmentDto;
import com.sena.crudbasic.repository.AppointmentRepository;
import com.sena.crudbasic.repository.DoctorRepository;
import com.sena.crudbasic.repository.PatientRepository;
import com.sena.crudbasic.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Appointment> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Appointment findById(int id) {
        return repository.findById(id)
                .filter(Appointment::isActive)
                .orElse(null);
    }

    @Override
    public List<Appointment> filterByStatus(String status) {
        return repository.filterByStatus(status);
    }

    @Override
    public String save(AppointmentDto a) {
        Appointment appointment = new Appointment();
        if (a.getId() != 0) {
            appointment = repository.findById(a.getId()).orElse(new Appointment());
        }
        appointment.setAppointmentDate(a.getAppointmentDate());
        appointment.setReason(a.getReason());
        appointment.setStatus(a.getStatus());
        if (a.getPatientId() != 0) {
            Patient patient = patientRepository.findById(a.getPatientId())
                .orElse(null);
            appointment.setPatient(patient);
        }
        if (a.getDoctorId() != 0) {
            Doctor doctor = doctorRepository.findById(a.getDoctorId())
                .orElse(null);
            appointment.setDoctor(doctor);
        }
        appointment.setActive(true);
        repository.save(appointment);
        return "Cita guardada correctamente";
    }

    @Override
    public String delete(int id) {
        Appointment appointment = repository.findById(id).orElse(null);
        if (appointment != null && appointment.isActive()) {
            appointment.setActive(false);
            repository.save(appointment);
            return "Cita eliminada correctamente";
        }
        return "Cita no encontrada o ya eliminada";
    }
}