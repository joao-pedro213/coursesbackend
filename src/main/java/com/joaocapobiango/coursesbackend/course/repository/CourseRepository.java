package com.joaocapobiango.coursesbackend.course.repository;

import com.joaocapobiango.coursesbackend.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);

    List<Course> findByCategory(String category);

    List<Course> findByNameAndCategory(String name, String category);

}