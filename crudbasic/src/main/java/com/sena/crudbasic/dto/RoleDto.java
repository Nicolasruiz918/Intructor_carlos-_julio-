package com.sena.crudbasic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto {

    private int id;

    private String roleName;

    private boolean isActive = true;
}