package com.prueba.capacitaciones.services.impl;

import com.prueba.capacitaciones.dto.modules.ListModuleResponseDto;
import com.prueba.capacitaciones.dto.modules.ModuleResponseDto;
import com.prueba.capacitaciones.entity.ModuleEntity;
import com.prueba.capacitaciones.repository.ModuleRepository;
import com.prueba.capacitaciones.services.IModuleServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServicesImpl implements IModuleServices {

    private final ModuleRepository moduleRepository;

    @Override
    public ListModuleResponseDto findAll() {
        List<ModuleResponseDto> moduleDtos = moduleRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();

        return ListModuleResponseDto.builder()
                .modules(moduleDtos)
                .build();
    }

    private ModuleResponseDto mapToDto(ModuleEntity module) {
        return ModuleResponseDto.builder()
                .id(Math.toIntExact(module.getId()))
                .nombre(module.getName())
                .build();
    }
}

