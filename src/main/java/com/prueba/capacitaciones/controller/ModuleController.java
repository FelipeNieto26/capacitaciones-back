package com.prueba.capacitaciones.controller;

import com.prueba.capacitaciones.dto.modules.ListModuleResponseDto;
import com.prueba.capacitaciones.dto.modules.ModuleResponseDto;
import com.prueba.capacitaciones.dto.utilidades.GenericResponseDto;
import com.prueba.capacitaciones.services.IModuleServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("capacitaciones")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ModuleController {

    private final IModuleServices moduleService;

    @GetMapping("/listar-modulos")
    public ResponseEntity<GenericResponseDto<List<ModuleResponseDto>>> findAll() {

        log.info("Consultando todos los modulos");
        List<ModuleResponseDto> modules = moduleService.findAll().getModules();

        GenericResponseDto<List<ModuleResponseDto>> genericResponse =
                GenericResponseDto.<List<ModuleResponseDto>>builder()
                        .codeResponse(200)
                        .message("Consulta exitosa")
                        .detail(modules)
                        .build();

        return ResponseEntity.ok(genericResponse);
    }
}
