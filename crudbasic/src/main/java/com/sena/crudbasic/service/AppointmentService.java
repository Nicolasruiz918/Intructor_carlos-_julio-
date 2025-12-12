package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.Appointment;
import com.sena.crudbasic.dto.AppointmentDto;

public interface AppointmentService {

    public List<Appointment> findAll();
    public Appointment findById(int id);
    public List<Appointment> filterByStatus(String status);
    public String save(AppointmentDto a);
    public String delete(int id);
}