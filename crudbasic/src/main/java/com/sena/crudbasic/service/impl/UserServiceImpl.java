
package com.sena.crudbasic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crudbasic.Model.User;
import com.sena.crudbasic.dto.UserDto;
import com.sena.crudbasic.repository.UserRepository;
import com.sena.crudbasic.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public User findById(int id) {
        return repository.findById(id)
                .filter(User::isActive)
                .orElse(null);
    }

    @Override
    public List<User> filterByUsername(String username) {
        return repository.filterByUsername(username);
    }

    @Override
    public String save(UserDto u) {
        User user = new User();
        if (u.getId() != 0) {
            user = repository.findById(u.getId()).orElse(new User());
        }
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setEmail(u.getEmail());
        user.setEnabled(u.isEnabled());
        user.setActive(true);
        repository.save(user);
        return "Usuario guardado correctamente";
    }

    @Override
    public String delete(int id) {
        User user = repository.findById(id).orElse(null);
        if (user != null && user.isActive()) {
            user.setActive(false);
            repository.save(user);
            return "Usuario eliminado correctamente";
        }
        return "Usuario no encontrado o ya eliminado";
    }
}