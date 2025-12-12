
package com.sena.crudbasic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HospitalRoomDto {

    private int id;

    private String roomNumber;

    private String type;

    private boolean occupied;

    private boolean isActive = true;
}