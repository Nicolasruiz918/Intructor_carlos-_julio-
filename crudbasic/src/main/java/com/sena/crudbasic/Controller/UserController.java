// UserController.java
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

import com.sena.crudbasic.Model.User;
import com.sena.crudbasic.dto.UserDto;
import com.sena.crudbasic.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id) {
        User u = service.findById(id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<User>> filterByUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.filterByUsername(username));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody UserDto u) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(u));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody UserDto u) {
        u.setId(id);
        return ResponseEntity.ok(service.save(u));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}