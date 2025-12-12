// DiagnosisController.java
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

import com.sena.crudbasic.Model.Diagnosis;
import com.sena.crudbasic.dto.DiagnosisDto;
import com.sena.crudbasic.service.DiagnosisService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/diagnoses")
@CrossOrigin(origins = "*")
public class DiagnosisController {

    @Autowired
    private DiagnosisService service;

    @GetMapping
    public ResponseEntity<List<Diagnosis>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnosis> findById(@PathVariable int id) {
        Diagnosis d = service.findById(id);
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<List<Diagnosis>> filterByCode(@PathVariable String code) {
        return ResponseEntity.ok(service.filterByCode(code));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody DiagnosisDto d) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody DiagnosisDto d) {
        d.setId(id);
        return ResponseEntity.ok(service.save(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}