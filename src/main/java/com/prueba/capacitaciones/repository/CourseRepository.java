package com.prueba.capacitaciones.repository;

import com.prueba.capacitaciones.entity.CourseEntity;
import com.prueba.capacitaciones.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    @Query("SELECT u FROM CourseEntity u WHERE u.module.id = :moduleId")
    List<CourseEntity> findByModuleId(@Param("moduleId") Long moduleId);


}
