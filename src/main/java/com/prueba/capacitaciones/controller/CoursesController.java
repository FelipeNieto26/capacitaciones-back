package com.prueba.capacitaciones.controller;

import com.prueba.capacitaciones.dto.courses.*;
import com.prueba.capacitaciones.dto.utilidades.GenericResponseDto;
import com.prueba.capacitaciones.services.ICoursesServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("capacitaciones")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CoursesController {

    private final ICoursesServices coursesServices;

    @GetMapping("/consultar-cursos")
    public ResponseEntity<GenericResponseDto<List<CoursesResponseDto>>> findCourseByModule(
            @RequestParam("moduleId") Integer moduleId
    ) {
        log.info("Consultando cursos por módulo: {}", moduleId);

        ListCoursesResponseDto response = coursesServices.getCoursesByIdModule(moduleId);

        GenericResponseDto<List<CoursesResponseDto>> body = GenericResponseDto.<List<CoursesResponseDto>>builder()
                .codeResponse(HttpStatus.OK.value())
                .message("Consulta exitosa")
                .detail(response.getCourses())
                .build();

        return ResponseEntity.ok(body);
    }

    @PatchMapping("/actualizar-estado")
    public ResponseEntity<GenericResponseDto<String>> actualizarEstado(
            @RequestBody UpdateCourseRequestDto updateCourseRequestDto
    ) {
        log.info("Actualizando estado");
        String result = coursesServices.updateCourse(updateCourseRequestDto);

        return ResponseEntity.ok(GenericResponseDto.<String>builder()
                .codeResponse(HttpStatus.OK.value())
                .message(result)
                .detail(null)
                .build());
    }

    @PostMapping("/iniciar-curso")
    public ResponseEntity<GenericResponseDto<String>> iniciarCurso(
            @RequestBody UpdateCourseRequestDto updateCourseRequestDto
    ) {
        log.info("Iniciando curso");
        String result = coursesServices.initCourse(updateCourseRequestDto);

        return ResponseEntity.ok(GenericResponseDto.<String>builder()
                .codeResponse(HttpStatus.OK.value())
                .message(result)
                .detail(null)
                .build());
    }

    @PostMapping("/agregar-curso")
    public ResponseEntity<GenericResponseDto<String>> agregarCurso(
            @RequestBody AddCourseRequestDto addCourseRequestDto
    ) {
        log.info("Agregando curso");
        String result = coursesServices.addCourse(addCourseRequestDto);

        return ResponseEntity.ok(GenericResponseDto.<String>builder()
                .codeResponse(HttpStatus.OK.value())
                .message(result)
                .detail(null)
                .build());
    }

    @PatchMapping("/editar-curso")
    public ResponseEntity<GenericResponseDto<String>> editarCurso(
            @RequestBody UpdateCourseDto updateCourseDto
    ) {
        log.info("Editando curso id={}", updateCourseDto.getId());
        String result = coursesServices.updateCourseInfo(updateCourseDto);

        return ResponseEntity.ok(GenericResponseDto.<String>builder()
                .codeResponse(HttpStatus.OK.value())
                .message(result)
                .detail(null)
                .build());
    }

    @GetMapping("/buscar-curso-id")
    public ResponseEntity<GenericResponseDto<Object>> buscarCursoId(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("userId") Integer userId
    ) {
        GenericResponseDto<Object> body = GenericResponseDto.builder()
                .codeResponse(HttpStatus.NOT_IMPLEMENTED.value())
                .message("Endpoint no implementado")
                .detail(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(body);
    }
}
