// SpecialtyController.java
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

import com.sena.crudbasic.Model.Specialty;
import com.sena.crudbasic.dto.SpecialtyDto;
import com.sena.crudbasic.service.SpecialtyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/specialties")
@CrossOrigin(origins = "*")
public class SpecialtyController {

    @Autowired
    private SpecialtyService service;

    @GetMapping
    public ResponseEntity<List<Specialty>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialty> findById(@PathVariable int id) {
        Specialty s = service.findById(id);
        return s != null ? ResponseEntity.ok(s) : ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Specialty>> filterByName(@PathVariable String name) {
        return ResponseEntity.ok(service.filterByName(name));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody SpecialtyDto s) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(s));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody SpecialtyDto s) {
        s.setId(id);
        return ResponseEntity.ok(service.save(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}