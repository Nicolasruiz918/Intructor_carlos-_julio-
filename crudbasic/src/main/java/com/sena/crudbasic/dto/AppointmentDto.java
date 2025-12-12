package com.sena.crudbasic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppointmentDto {

    private int id;

    @NotBlank(message = "Fecha de cita obligatoria")
    private String appointmentDate; 
    
    private String reason;

    private String status;

    private int patientId;

    private String patientName;

    private int doctorId;

    private String doctorName;

    private boolean isActive = true;
}