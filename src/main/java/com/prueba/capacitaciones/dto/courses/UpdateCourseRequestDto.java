package com.prueba.capacitaciones.dto.courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateCourseRequestDto {

    private Long userId;
    private Long courseId;
    private String status;
}
