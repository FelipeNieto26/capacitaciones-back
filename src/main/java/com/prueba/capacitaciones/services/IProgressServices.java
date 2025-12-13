package com.prueba.capacitaciones.services;

import com.prueba.capacitaciones.dto.progress.ProgressResponseDto;
import com.prueba.capacitaciones.dto.progress.ProgressResponseUserDto;

import java.util.List;

public interface IProgressServices {

    ProgressResponseDto listProgress(Long userId, Long courseId);

    List<ProgressResponseUserDto> listProgressByUser(Long userId);

}
