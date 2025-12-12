package com.sena.crudbasic.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = "role_name"))
@AttributeOverride(name = "id", column = @Column(name = "id_role"))
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    @NotBlank(message = "Nombre del rol obligatorio")
    @Size(max = 50)
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
}