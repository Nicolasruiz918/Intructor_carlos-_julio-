package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.User;
import com.sena.crudbasic.dto.UserDto;

public interface UserService {

    public List<User> findAll();
    public User findById(int id);
    public List<User> filterByUsername(String username);
    public String save(UserDto u);
    public String delete(int id);
}