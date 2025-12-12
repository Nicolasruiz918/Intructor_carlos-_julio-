
package com.sena.crudbasic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdmissionDto {

    private int id;

    private String admissionDate;
    private String dischargeDate;

    private int patientId;
    private String patientName;

    private int roomId;
    private String roomNumber;

    private boolean isActive = true;
}