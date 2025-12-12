
package com.sena.crudbasic.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
})
@AttributeOverride(name = "id", column = @Column(name = "id_users"))
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @NotBlank(message = "Username obligatorio")
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @NotBlank(message = "Password obligatorio")
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Email(message = "Email inválido")
    @Column(unique = true)
    private String email;

    private boolean enabled = true;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserRole> roles;
}