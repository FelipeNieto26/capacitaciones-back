package com.prueba.capacitaciones.dto.progress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProgressResponseDto {

    private int id;
    private int userId;
    private int courseId;
    private String status;
}
