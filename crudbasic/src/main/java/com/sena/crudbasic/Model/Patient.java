package com.sena.crudbasic.Model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "patient", uniqueConstraints = @UniqueConstraint(columnNames = "dni"))
@AttributeOverride(name = "id", column = @Column(name = "id_patient"))
@Data
@EqualsAndHashCode(callSuper = true)
public class Patient extends BaseEntity {

    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @NotBlank(message = "Nombre completo es obligatorio")
    @Size(max = 100)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_date")
    private String birthDate;

    @Pattern(regexp = "^\\d{10}$", message = "Teléfono debe tener 10 dígitos")
    @Column(length = 20)
    private String phone;

    @Column(name = "blood_type", length = 5)
    private String bloodType;

    @Size(max = 200)
    @Column(length = 200)
    private String address;
}
