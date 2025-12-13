package com.prueba.capacitaciones.dto.courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddCourseRequestDto {

    public String name;
    public String description;
    public int moduleId;
}
