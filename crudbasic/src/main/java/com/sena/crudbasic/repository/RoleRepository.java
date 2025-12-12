package com.sena.crudbasic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.crudbasic.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findByIsActiveTrue();

    @Query(""
            + "SELECT "
            + "r "
            + "FROM "
            + "Role r "
            + "WHERE "
            + "LOWER(r.roleName) LIKE LOWER(CONCAT('%', ?1, '%')) "
            + "AND r.isActive = true")
    public List<Role> filterByRoleName(String roleName);
}