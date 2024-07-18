package com.joaocapobiango.coursesbackend.course.service;

import com.joaocapobiango.coursesbackend.course.dto.CoursePostRequest;
import com.joaocapobiango.coursesbackend.course.dto.CourseResponse;
import com.joaocapobiango.coursesbackend.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "account.id", source = "accountId")
    Course postRequestToEntity(CoursePostRequest request);

    @Mapping(target = "accountId", source = "account.id")
    CourseResponse toResponse(Course course);

    @Mapping(target = "accountId", source = "account.id")
    List<CourseResponse> toResponses(List<Course> courses);

}