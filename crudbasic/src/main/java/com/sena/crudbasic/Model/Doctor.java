
package com.sena.crudbasic.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "doctor")
@AttributeOverride(name = "id", column = @Column(name = "id_doctor"))
@Data
@EqualsAndHashCode(callSuper = true)
public class Doctor extends BaseEntity {

    @NotBlank(message = "Nombre es obligatorio")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Número de licencia es obligatorio")
    @Column(name = "license_number")
    private String licenseNumber;

    @Pattern(regexp = "^\\d{10}$", message = "Teléfono debe tener 10 dígitos")
    @Column(length = 20)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "id_specialty")
    private Specialty specialty;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<MedicalRecord> medicalRecords;
}