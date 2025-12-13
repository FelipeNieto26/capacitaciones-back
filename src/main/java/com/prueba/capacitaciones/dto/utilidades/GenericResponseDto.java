package com.prueba.capacitaciones.dto.utilidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GenericResponseDto<T> {
    private Integer codeResponse;
    private String message;
    private T detail;
}
