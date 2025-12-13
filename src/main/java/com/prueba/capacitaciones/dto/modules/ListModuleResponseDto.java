package com.prueba.capacitaciones.dto.modules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ListModuleResponseDto {

    List<ModuleResponseDto> modules;
}
