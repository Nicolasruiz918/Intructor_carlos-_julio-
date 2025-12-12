package com.sena.crudbasic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDto {

    private int id;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 20, message = "DNI debe tener entre 8 y 20 caracteres")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    private String fullName;

    private String birthDate; 

    @Pattern(regexp = "^\\d{10}$", message = "Teléfono debe tener 10 dígitos")
    private String phone;

    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Tipo de sangre inválido")
    private String bloodType;

    private String address;

    private boolean isActive = true;
}