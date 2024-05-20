package com.joaocapobiango.coursesbackend.course.service;

import com.joaocapobiango.coursesbackend.course.dto.CoursePostRequest;
import com.joaocapobiango.coursesbackend.course.dto.CourseResponse;
import com.joaocapobiango.coursesbackend.course.entity.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course postRequestToEntity(CoursePostRequest request);

    CourseResponse toResponse(Course course);

    List<CourseResponse> toResponses(List<Course> courses);

}