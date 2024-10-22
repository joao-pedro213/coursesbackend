package com.joaocapobiango.coursesbackend.course.repository;

import com.joaocapobiango.coursesbackend.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByIdAndAccountId(Long id, Long accountId);

    List<Course> findByName(String name);

    List<Course> findByCategory(String category);

    List<Course> findByNameAndCategory(String name, String category);

}