package com.sena.crudbasic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorDto {

    private int id;

    @NotBlank(message = "Nombre del doctor obligatorio")
    private String fullName;

    @NotBlank(message = "Número de licencia obligatorio")
    private String licenseNumber;

    @Pattern(regexp = "^\\d{10}$", message = "Teléfono debe tener 10 dígitos")
    private String phone;

    private int specialtyId; 

    private String specialtyName; 
    private boolean isActive = true;
}