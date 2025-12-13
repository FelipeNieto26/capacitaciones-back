package com.prueba.capacitaciones.repository;

import com.prueba.capacitaciones.entity.UserCourseProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCourseProgressRepository extends JpaRepository<UserCourseProgressEntity, Integer> {


    @Query("SELECT u FROM UserCourseProgressEntity u " +
            "WHERE u.user.id = :userId AND u.course.id = :courseId")
    List<UserCourseProgressEntity> findByUserAndCourse(@Param("userId") Long userId,
                                                       @Param("courseId") Long courseId);


    @Query("SELECT u FROM UserCourseProgressEntity u " +
            "WHERE u.user.id = :userId AND u.course.id = :courseId")
    UserCourseProgressEntity findProgress(
            @Param("userId") Long userId,
            @Param("courseId") Long courseId
    );

    @Query("SELECT u FROM UserCourseProgressEntity u WHERE u.user.id = :userId")
    List<UserCourseProgressEntity> findAllByUserId(@Param("userId") Long userId);
}
