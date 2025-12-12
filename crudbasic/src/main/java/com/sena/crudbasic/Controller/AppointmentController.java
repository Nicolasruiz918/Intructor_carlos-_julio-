// AppointmentController.java
package com.sena.crudbasic.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crudbasic.Model.Appointment;
import com.sena.crudbasic.dto.AppointmentDto;
import com.sena.crudbasic.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @GetMapping
    public ResponseEntity<List<Appointment>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable int id) {
        Appointment a = service.findById(id);
        return a != null ? ResponseEntity.ok(a) : ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Appointment>> filterByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.filterByStatus(status));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody AppointmentDto a) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(a));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody AppointmentDto a) {
        a.setId(id);
        return ResponseEntity.ok(service.save(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}