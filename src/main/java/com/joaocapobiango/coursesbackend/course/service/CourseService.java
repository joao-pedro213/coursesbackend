package com.joaocapobiango.coursesbackend.course.service;

import com.joaocapobiango.coursesbackend.course.dto.CourseCreationRequest;
import com.joaocapobiango.coursesbackend.course.dto.CourseCreationResponse;
import com.joaocapobiango.coursesbackend.course.dto.CourseResponse;
import com.joaocapobiango.coursesbackend.course.entity.Course;
import com.joaocapobiango.coursesbackend.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public CourseCreationResponse create(CourseCreationRequest request) {
        var courseToInsert = this.creationRequestToEntity(request);
        var insertedCourse = this.repository.save(courseToInsert);
        return this.toCreationResponse(insertedCourse);
    }

    public List<CourseResponse> getAll() {
        var foundCourses = this.repository.findAll();
        return this.toResponses(foundCourses);
    }

    private Course creationRequestToEntity(CourseCreationRequest request) {
        return Course
            .builder()
            .name(request.getName())
            .category(request.getCategory())
            .build();
    }

    private CourseCreationResponse toCreationResponse(Course course) {
        return CourseCreationResponse
            .builder()
            .id(course.getId())
            .createdAt(course.getCreatedAt())
            .build();
    }

    private List<CourseResponse> toResponses(List<Course> courses) {
        var responses = new ArrayList<CourseResponse>();
        for (Course course : courses) {
           var response = CourseResponse
                .builder()
                .id(course.getId())
                .name(course.getName())
                .category(course.getCategory())
                .status(course.getStatus().name())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
            responses.add(response);
        }
        return responses;
    }

}