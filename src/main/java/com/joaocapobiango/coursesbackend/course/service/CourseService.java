package com.joaocapobiango.coursesbackend.course.service;

import com.joaocapobiango.coursesbackend.course.dto.*;
import com.joaocapobiango.coursesbackend.course.entity.Course;
import com.joaocapobiango.coursesbackend.course.entity.CourseStatus;
import com.joaocapobiango.coursesbackend.course.repository.CourseRepository;
import com.joaocapobiango.coursesbackend.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseMapper mapper;

    public CourseResponse create(CoursePostRequest request) {
        var courseToInsert = this.mapper.postRequestToEntity(request);
        var insertedCourse = this.repository.save(courseToInsert);
        return this.mapper.toResponse(insertedCourse);
    }

    public List<CourseResponse> getAll(String name, String category) {
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
        return this.mapper.toResponses(foundCourses);
    }

    public CourseResponse update(Long id, CoursePutRequest request) {
        var courseToUpdate = this.repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
        if (request.getName() != null) {
            courseToUpdate.setName(request.getName());
        }
        if (request.getCategory() != null) {
            courseToUpdate.setCategory(request.getCategory());
        }
        var updatedCourse = this.repository.save(courseToUpdate);
       return this.mapper.toResponse(updatedCourse);
    }

    public CourseResponse toggleStatus(Long id) {
        var courseToUpdate = this.repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
        if (courseToUpdate.getStatus() == CourseStatus.ACTIVE) {
            courseToUpdate.setStatus(CourseStatus.INACTIVE);
        } else {
            courseToUpdate.setStatus(CourseStatus.ACTIVE);
        }
        var updatedCourse = this.repository.save(courseToUpdate);
        return this.mapper.toResponse(updatedCourse);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}