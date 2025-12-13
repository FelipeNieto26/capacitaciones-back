package com.prueba.capacitaciones.dto.modules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ModuleResponseDto {

    private int id;
    private String nombre;
}
