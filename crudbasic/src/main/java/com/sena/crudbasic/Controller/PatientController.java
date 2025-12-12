// PatientController.java
package com.sena.crudbasic.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crudbasic.Model.Patient;
import com.sena.crudbasic.dto.PatientDto;
import com.sena.crudbasic.service.PatientService;
import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable int id) {
        Patient p = service.findById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<List<Patient>> filterByDni(@PathVariable String dni) {
        return ResponseEntity.ok(service.filterByDni(dni));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Patient>> filterByFullName(@PathVariable String name) {
        return ResponseEntity.ok(service.filterByFullName(name));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody PatientDto p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody PatientDto p) {
        p.setId(id);
        return ResponseEntity.ok(service.save(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        String msg = service.delete(id);
        return ResponseEntity.ok(msg);
    }
}