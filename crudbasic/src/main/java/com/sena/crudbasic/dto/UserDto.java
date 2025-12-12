package com.sena.crudbasic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;

    @NotBlank(message = "Username obligatorio")
    @Size(min = 3, max = 50, message = "Username debe tener entre 3 y 50 caracteres")
    private String username;

    @NotBlank(message = "Password obligatorio")
    @Size(min = 6, message = "Password debe tener al menos 6 caracteres")
    private String password;

    @Email(message = "Email inválido")
    private String email;

    private boolean enabled = true;
}