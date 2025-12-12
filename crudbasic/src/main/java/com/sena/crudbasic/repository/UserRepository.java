
package com.sena.crudbasic.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sena.crudbasic.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByIsActiveTrue();

    @Query(""
            + "SELECT "
            + "u "
            + "FROM "
            + "User u "
            + "WHERE "
            + "LOWER(u.username) LIKE LOWER(CONCAT('%', ?1, '%')) "
            + "AND u.isActive = true")
    public List<User> filterByUsername(String username);

    Optional<User> findByUsername(String username);
}