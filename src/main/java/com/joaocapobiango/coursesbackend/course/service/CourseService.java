package com.joaocapobiango.coursesbackend.course.service;

import com.joaocapobiango.coursesbackend.course.dto.CoursePostRequest;
import com.joaocapobiango.coursesbackend.course.dto.CoursePostResponse;
import com.joaocapobiango.coursesbackend.course.dto.CourseGetResponse;
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

    public CoursePostResponse create(CoursePostRequest request) {
        var courseToInsert = this.creationRequestToEntity(request);
        var insertedCourse = this.repository.save(courseToInsert);
        return this.toCreationResponse(insertedCourse);
    }

    public List<CourseGetResponse> getCourses(String name, String category) {
        List<Course> foundCourses;
        if (name != null && category != null) {
            foundCourses = this.repository.findByNameAndCategory(name, category);
        } else if (name != null) {
            foundCourses = this.repository.findByName(name);
        } else if (category != null) {
            foundCourses = this.repository.findByCategory(category);
        } else {
            foundCourses = this.repository.findAll();
        }
        return this.toResponses(foundCourses);
    }

    private Course creationRequestToEntity(CoursePostRequest request) {
        return Course
            .builder()
            .name(request.getName())
            .category(request.getCategory())
            .build();
    }

    private CoursePostResponse toCreationResponse(Course course) {
        return CoursePostResponse
            .builder()
            .id(course.getId())
            .createdAt(course.getCreatedAt())
            .build();
    }

    private List<CourseGetResponse> toResponses(List<Course> courses) {
        var responses = new ArrayList<CourseGetResponse>();
        for (Course course : courses) {
           var response = CourseGetResponse
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