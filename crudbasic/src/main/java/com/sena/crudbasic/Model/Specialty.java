
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
@Table(name = "specialty", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@AttributeOverride(name = "id", column = @Column(name = "id_specialty"))
@Data
@EqualsAndHashCode(callSuper = true)
public class Specialty extends BaseEntity {

    @NotBlank(message = "Nombre de especialidad obligatorio")
    @Size(max = 80)
    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "specialty")
    private List<Doctor> doctors;
}