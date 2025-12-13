package com.prueba.capacitaciones.dto.courses;

import lombok.Data;

@Data
public class UpdateCourseDto {
    private Long id;
    private String name;
    private String description;
    private Long moduleId;
}