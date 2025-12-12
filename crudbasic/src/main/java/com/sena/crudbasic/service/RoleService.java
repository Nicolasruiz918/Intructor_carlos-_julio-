package com.sena.crudbasic.service;

import java.util.List;
import com.sena.crudbasic.Model.Role;
import com.sena.crudbasic.dto.RoleDto;

public interface RoleService {

    public List<Role> findAll();
    public Role findById(int id);
    public List<Role> filterByRoleName(String roleName);
    public String save(RoleDto r);
    public String delete(int id);
}