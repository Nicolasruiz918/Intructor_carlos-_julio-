package com.sena.crudbasic.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "hospital_room")
@AttributeOverride(name = "id", column = @Column(name = "id_room"))
@Data
@EqualsAndHashCode(callSuper = true)
public class HospitalRoom extends BaseEntity {

    @NotBlank(message = "Número de habitación obligatorio")
    @Size(max = 20)
    @Column(name = "room_number", unique = true, nullable = false)
    private String roomNumber;

    @NotBlank(message = "Tipo de habitación obligatorio")
    @Size(max = 50)
    @Column(length = 50)
    private String type;

    private boolean occupied = false;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<Admission> admissions;
}