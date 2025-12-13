package com.prueba.capacitaciones.dto.progress;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressResponseUserDto {
    private Long courseId;
    private String status;
}
