// MedicalRecordController.java
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

import com.sena.crudbasic.Model.MedicalRecord;
import com.sena.crudbasic.dto.MedicalRecordDto;
import com.sena.crudbasic.service.MedicalRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medical-records")
@CrossOrigin(origins = "*")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService service;

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> findById(@PathVariable int id) {
        MedicalRecord m = service.findById(id);
        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @GetMapping("/patient/{name}")
    public ResponseEntity<List<MedicalRecord>> filterByPatientName(@PathVariable String name) {
        return ResponseEntity.ok(service.filterByPatientName(name));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody MedicalRecordDto m) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(m));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody MedicalRecordDto m) {
        m.setId(id);
        return ResponseEntity.ok(service.save(m));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}