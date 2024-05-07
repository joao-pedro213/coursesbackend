package com.joaocapobiango.coursesbackend.course.repository;

import com.joaocapobiango.coursesbackend.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}