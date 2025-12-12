
package com.sena.crudbasic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiagnosisDto {

    private int id;

    private String code;

    private String description;

    private int recordId;

    private boolean isActive = true;
}