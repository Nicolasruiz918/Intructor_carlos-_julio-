package com.sena.crudbasic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpecialtyDto {

    private int id;

    private String name;

    private boolean isActive = true;
}