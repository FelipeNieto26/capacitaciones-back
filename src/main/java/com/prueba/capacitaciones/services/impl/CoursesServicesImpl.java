package com.prueba.capacitaciones.services.impl;

import com.prueba.capacitaciones.dto.courses.*;
import com.prueba.capacitaciones.entity.CourseEntity;
import com.prueba.capacitaciones.entity.ModuleEntity;
import com.prueba.capacitaciones.entity.UserCourseProgressEntity;
import com.prueba.capacitaciones.entity.UserEntity;
import com.prueba.capacitaciones.exceptions.ExceptionGlobalResponse;
import com.prueba.capacitaciones.repository.CourseRepository;
import com.prueba.capacitaciones.repository.ModuleRepository;
import com.prueba.capacitaciones.repository.UserCourseProgressRepository;
import com.prueba.capacitaciones.repository.UserRepository;
import com.prueba.capacitaciones.services.ICoursesServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursesServicesImpl implements ICoursesServices {

    private final CourseRepository courseRepository;
    private final UserCourseProgressRepository userCourseProgressRepository;
    private final ModuleRepository moduleRepository;
    private final UserRepository userRepository;

    @Override
    public ListCoursesResponseDto getCoursesByIdModule(Integer idModule) {

        List<CourseEntity> entities = courseRepository.findByModuleId(Long.valueOf(idModule));

        if (!moduleRepository.existsById(idModule)) {
            throw new ExceptionGlobalResponse.ModuleNotFoundException("Módulo no encontrado");
        }

        List<CoursesResponseDto> courses = entities.stream().map(e -> CoursesResponseDto.builder().id(Math.toIntExact(e.getId())).name(e.getName()).description(e.getDescription()).build()).toList();

        return ListCoursesResponseDto.builder().courses(courses).build();
    }

    @Override
    public String updateCourse(UpdateCourseRequestDto request) {

        UserCourseProgressEntity progress =
                userCourseProgressRepository.findProgress(request.getUserId(), request.getCourseId());

        if (progress == null) {
            return "No existe progreso registrado para este usuario y curso.";
        }
        if (!request.getStatus().equals("INICIADO") &&
                !request.getStatus().equals("COMPLETADO")) {
            return "Estado inválido. Solo se permite: INICIADO o COMPLETADO.";
        }
        progress.setStatus(request.getStatus());
        userCourseProgressRepository.save(progress);

        return "Estado actualizado correctamente";
    }

    @Override
    @Transactional
    public String initCourse(UpdateCourseRequestDto updateCourseRequestDto) {
        String status = updateCourseRequestDto.getStatus();
        if (!status.equals("INICIADO") && !status.equals("COMPLETADO")) {
            throw new IllegalArgumentException("El status solo puede ser INICIADO o COMPLETADO");
        }
        UserCourseProgressEntity progress =
                userCourseProgressRepository.findProgress(
                        updateCourseRequestDto.getUserId(),
                        updateCourseRequestDto.getCourseId()
                );
        if (progress != null) {
            return "El usuario ya tiene un progreso registrado para este curso";
        }
        UserEntity user = userRepository.findById(Math.toIntExact(updateCourseRequestDto.getUserId()))
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));
        CourseEntity course = courseRepository.findById(Math.toIntExact(updateCourseRequestDto.getCourseId()))
                .orElseThrow(() -> new RuntimeException("Curso no existe"));
        UserCourseProgressEntity newProgress = UserCourseProgressEntity.builder()
                .user(user)
                .course(course)
                .status(status)
                .build();
        userCourseProgressRepository.save(newProgress);
        return "Progreso creado correctamente";
    }


    @Override
    public String addCourse(AddCourseRequestDto addCourseRequestDto) {

        CourseEntity course = new CourseEntity();

        course.setName(addCourseRequestDto.getName());
        course.setDescription(addCourseRequestDto.getDescription());
        Optional<ModuleEntity> module = moduleRepository.findById(addCourseRequestDto.moduleId);
        course.setModule(module.get());

        courseRepository.save(course);

        return "Curso Creado Correctamente";
    }

    @Override
    public String updateCourseInfo(UpdateCourseDto request) {

        Optional<CourseEntity> optCourse = courseRepository.findById(Math.toIntExact(request.getId()));

        if (optCourse.isEmpty()) {
            return "El curso no existe.";
        }
        CourseEntity course = optCourse.get();
        if (request.getName() == null || request.getName().isBlank()) {
            return "El nombre del curso es obligatorio.";
        }
        if (request.getModuleId() == null) {
            return "El módulo es obligatorio.";
        }
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        Optional<ModuleEntity> module = moduleRepository.findById(Math.toIntExact(request.getModuleId()));

        course.setModule(module.get());

        courseRepository.save(course);

        return "Curso actualizado correctamente";
    }


}
