package com.prueba.capacitaciones.services;

import com.prueba.capacitaciones.dto.courses.AddCourseRequestDto;
import com.prueba.capacitaciones.dto.courses.ListCoursesResponseDto;
import com.prueba.capacitaciones.dto.courses.UpdateCourseDto;
import com.prueba.capacitaciones.dto.courses.UpdateCourseRequestDto;

public interface ICoursesServices {

    ListCoursesResponseDto getCoursesByIdModule(Integer idModule);

    String updateCourse(UpdateCourseRequestDto updateCourseRequestDto);

    String initCourse(UpdateCourseRequestDto updateCourseRequestDto);

    String addCourse(AddCourseRequestDto addCourseRequestDto);

    String updateCourseInfo(UpdateCourseDto request);

}
