package com.joaocapobiango.coursesbackend.course.service;

import com.joaocapobiango.coursesbackend.course.dto.*;
import com.joaocapobiango.coursesbackend.course.entity.Course;
import com.joaocapobiango.coursesbackend.course.repository.CourseRepository;
import com.joaocapobiango.coursesbackend.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public CoursePostResponse create(CoursePostRequest request) {
        var courseToInsert = this.postRequestToEntity(request);
        var insertedCourse = this.repository.save(courseToInsert);
        return this.toPostResponse(insertedCourse);
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

    public CoursePatchResponse update(Long id, CoursePatchRequest request) {
        var courseToUpdate = this.repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
        if (request.getName() != null) {
            courseToUpdate.setName(request.getName());
        }
        if (request.getCategory() != null) {
            courseToUpdate.setCategory(request.getCategory());
        }
        var updatedCourse = this.repository.save(courseToUpdate);
       return this.toPatchResponse(updatedCourse);
    }

    private Course postRequestToEntity(CoursePostRequest request) {
        return Course
            .builder()
            .name(request.getName())
            .category(request.getCategory())
            .build();
    }

    private CoursePostResponse toPostResponse(Course course) {
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

    private CoursePatchResponse toPatchResponse(Course course) {
        return CoursePatchResponse
            .builder()
            .id(course.getId())
            .updatedAt(course.getUpdatedAt())
            .build();
    }

}