
package com.sena.crudbasic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicalRecordDto {

    private int id;

    private String recordDate;

    private String symptoms;

    private String observations;

    private int patientId;
    private String patientName;

    private int doctorId;
    private String doctorName;

    private boolean isActive = true;
}