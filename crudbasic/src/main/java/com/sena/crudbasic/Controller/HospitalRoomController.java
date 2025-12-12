// HospitalRoomController.java
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

import com.sena.crudbasic.Model.HospitalRoom;
import com.sena.crudbasic.dto.HospitalRoomDto;
import com.sena.crudbasic.service.HospitalRoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "*")
public class HospitalRoomController {

    @Autowired
    private HospitalRoomService service;

    @GetMapping
    public ResponseEntity<List<HospitalRoom>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<HospitalRoom>> findAvailableRooms() {
        return ResponseEntity.ok(service.findAvailableRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalRoom> findById(@PathVariable int id) {
        HospitalRoom r = service.findById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody HospitalRoomDto r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody HospitalRoomDto r) {
        r.setId(id);
        return ResponseEntity.ok(service.save(r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.delete(id));
    }
}