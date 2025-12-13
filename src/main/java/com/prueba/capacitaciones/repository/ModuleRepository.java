package com.prueba.capacitaciones.repository;

import com.prueba.capacitaciones.entity.CourseEntity;
import com.prueba.capacitaciones.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleRepository extends JpaRepository<ModuleEntity, Integer> {

    @Query("SELECT u FROM ModuleEntity u WHERE u.id = :moduleId")
    List<ModuleEntity> findByModuleId(Long moduleId);
}
