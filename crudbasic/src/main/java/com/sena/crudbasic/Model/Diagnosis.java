
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
@Table(name = "diagnosis")
@AttributeOverride(name = "id", column = @Column(name = "id_diagnosis"))
@Data
@EqualsAndHashCode(callSuper = true)
public class Diagnosis extends BaseEntity {

    @Size(max = 10)
    @Column(name = "code", length = 10)
    private String code;

    @Size(max = 500)
    @Column(length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_record")
    private MedicalRecord record;
}