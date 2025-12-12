
package com.sena.crudbasic.Model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "medical_record")
@AttributeOverride(name = "id", column = @Column(name = "id_record"))
@Data
@EqualsAndHashCode(callSuper = true)
public class MedicalRecord extends BaseEntity {

    @Column(name = "record_date")
    private String recordDate;

    @Size(max = 2000)
    @Column(length = 2000)
    private String symptoms;

    @Size(max = 3000)
    @Column(length = 3000)
    private String observations;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
}