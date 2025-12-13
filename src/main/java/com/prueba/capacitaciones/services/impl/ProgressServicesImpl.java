package com.prueba.capacitaciones.services.impl;

import com.prueba.capacitaciones.dto.progress.ProgressResponseDto;
import com.prueba.capacitaciones.dto.progress.ProgressResponseUserDto;
import com.prueba.capacitaciones.entity.UserCourseProgressEntity;
import com.prueba.capacitaciones.repository.UserCourseProgressRepository;
import com.prueba.capacitaciones.services.IProgressServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressServicesImpl implements IProgressServices {

    private final UserCourseProgressRepository userCourseProgressRepository;

    @Override
    public ProgressResponseDto listProgress(Long userId, Long courseId) {

        List<UserCourseProgressEntity> progressList =
                userCourseProgressRepository.findByUserAndCourse(userId, courseId);

        if (progressList.isEmpty()) {
            return null;
        }

        UserCourseProgressEntity progressEntity = progressList.get(0);

        return ProgressResponseDto.builder()
                .id(progressEntity.getId().intValue())
                .userId(progressEntity.getUser().getId().intValue())
                .courseId(progressEntity.getCourse().getId().intValue())
                .status(progressEntity.getStatus())
                .build();
    }

    @Override
    public List<ProgressResponseUserDto> listProgressByUser(Long userId) {
        return userCourseProgressRepository.findAllByUserId(userId)
                .stream()
                .map(p -> ProgressResponseUserDto.builder()
                        .courseId(p.getCourse().getId())
                        .status(p.getStatus())
                        .build())
                .toList();
    }
}
