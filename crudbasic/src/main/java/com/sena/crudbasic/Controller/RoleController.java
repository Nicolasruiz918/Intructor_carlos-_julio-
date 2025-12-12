// RoleController.java
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

import com.sena.crudbasic.Model.Role;
import com.sena.crudbasic.dto.RoleDto;
import com.sena.crudbasic.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable int id) {
        Role r = service.findById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Role>> filterByRoleName(@PathVariable String name) {
        return ResponseEntity.ok(service.filterByRoleName(name));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody RoleDto r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody RoleDto r) {
        r.setId(id);
        return ResponseEntity.ok(service.save(r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}