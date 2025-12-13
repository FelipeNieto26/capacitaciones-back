package com.prueba.capacitaciones.controller;

import com.prueba.capacitaciones.dto.progress.ProgressResponseDto;
import com.prueba.capacitaciones.dto.progress.ProgressResponseUserDto;
import com.prueba.capacitaciones.dto.utilidades.GenericResponseDto;
import com.prueba.capacitaciones.services.IProgressServices;
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
public class ProgressController {

    private final IProgressServices progressServices;

    @GetMapping("/progress")
    public ResponseEntity<GenericResponseDto<ProgressResponseDto>> getUserCourseProgress(
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") Long courseId) {

        log.info("Buscando progreso del usuario {} para el curso {}", userId, courseId);

        ProgressResponseDto progress = progressServices.listProgress(userId, courseId);

        if (progress == null) {
            GenericResponseDto<ProgressResponseDto> notFound = GenericResponseDto.<ProgressResponseDto>builder()
                    .codeResponse(HttpStatus.NOT_FOUND.value())
                    .message("Progreso no encontrado")
                    .detail(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

        GenericResponseDto<ProgressResponseDto> response = GenericResponseDto.<ProgressResponseDto>builder()
                .codeResponse(HttpStatus.OK.value())
                .message("OK")
                .detail(progress)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/progreso-usuario")
    public ResponseEntity<GenericResponseDto<List<ProgressResponseUserDto>>> getUserProgress(
            @RequestParam("userId") Long userId) {

        log.info("Buscando progreso del usuario {}", userId);

        List<ProgressResponseUserDto> progress = progressServices.listProgressByUser(userId);

        GenericResponseDto<List<ProgressResponseUserDto>> response = GenericResponseDto.<List<ProgressResponseUserDto>>builder()
                .codeResponse(HttpStatus.OK.value())
                .message("OK")
                .detail(progress)
                .build();

        return ResponseEntity.ok(response);
    }

}
