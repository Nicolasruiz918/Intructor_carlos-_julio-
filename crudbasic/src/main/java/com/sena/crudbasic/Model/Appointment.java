
package com.sena.crudbasic.Model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "appointment")
@AttributeOverride(name = "id", column = @Column(name = "id_appointment"))
@Data
@EqualsAndHashCode(callSuper = true)
public class Appointment extends BaseEntity {

    @NotBlank(message = "Fecha de cita obligatoria")
    @Column(name = "appointment_date", nullable = false)
    private String appointmentDate;

    @Size(max = 500)
    @Column(length = 500)
    private String reason;

    @Size(max = 20)
    @Column(length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
}