
package com.sena.crudbasic.Model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admission")
@AttributeOverride(name = "id", column = @Column(name = "id_admission"))
@Data
@EqualsAndHashCode(callSuper = true)
public class Admission extends BaseEntity {

    @NotBlank(message = "Fecha de admisión obligatoria")
    @Column(name = "admission_date")
    private String admissionDate;

    @Column(name = "discharge_date")
    private String dischargeDate;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_room")
    private HospitalRoom room;
}