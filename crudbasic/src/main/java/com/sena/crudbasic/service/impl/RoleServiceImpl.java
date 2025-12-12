package com.sena.crudbasic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crudbasic.Model.Role;
import com.sena.crudbasic.dto.RoleDto;
import com.sena.crudbasic.repository.RoleRepository;
import com.sena.crudbasic.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Role findById(int id) {
        return repository.findById(id)
                .filter(Role::isActive)
                .orElse(null);
    }

    @Override
    public List<Role> filterByRoleName(String roleName) {
        return repository.filterByRoleName(roleName);
    }

    @Override
    public String save(RoleDto r) {
        Role role = new Role();
        if (r.getId() != 0) {
            role = repository.findById(r.getId()).orElse(new Role());
        }
        role.setRoleName(r.getRoleName());
        role.setActive(true);
        repository.save(role);
        return "Rol guardado correctamente";
    }

    @Override
    public String delete(int id) {
        return repository.findById(id)
                .filter(Role::isActive)
                .map( r -> {
                    r.setActive(false);
                    repository.save(r);
                    return "Rol eliminado correctamente";
                })
                .orElse("Rol no encontrado o ya eliminado");
    }
}