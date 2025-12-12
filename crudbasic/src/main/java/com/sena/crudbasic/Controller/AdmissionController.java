// AdmissionController.java
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

import com.sena.crudbasic.Model.Admission;
import com.sena.crudbasic.dto.AdmissionDto;
import com.sena.crudbasic.service.AdmissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admissions")
@CrossOrigin(origins = "*")
public class AdmissionController {

    @Autowired
    private AdmissionService service;

    @GetMapping
    public ResponseEntity<List<Admission>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Admission>> findActiveAdmissions() {
        return ResponseEntity.ok(service.findActiveAdmissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admission> findById(@PathVariable int id) {
        Admission a = service.findById(id);
        return a != null ? ResponseEntity.ok(a) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody AdmissionDto a) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(a));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody AdmissionDto a) {
        a.setId(id);
        return ResponseEntity.ok(service.save(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}