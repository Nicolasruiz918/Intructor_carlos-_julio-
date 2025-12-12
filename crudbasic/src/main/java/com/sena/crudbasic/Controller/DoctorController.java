// DoctorController.java
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

import com.sena.crudbasic.Model.Doctor;
import com.sena.crudbasic.dto.DoctorDto;
import com.sena.crudbasic.service.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @GetMapping
    public ResponseEntity<List<Doctor>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> findById(@PathVariable int id) {
        Doctor d = service.findById(id);
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<List<Doctor>> filterBySpecialty(@PathVariable String specialty) {
        return ResponseEntity.ok(service.filterBySpecialty(specialty));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody DoctorDto d) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody DoctorDto d) {
        d.setId(id);
        return ResponseEntity.ok(service.save(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}