package com.joaocapobiango.coursesbackend.course.controller;

import com.joaocapobiango.coursesbackend.course.dto.*;
import com.joaocapobiango.coursesbackend.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/app/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    public ResponseEntity<CoursePostResponse> create(@RequestBody CoursePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseGetResponse>> get(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getCourses(name, category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoursePutResponse> update(
            @PathVariable Long id,
            @RequestBody CoursePutRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.update(id, request));
    }

    @PatchMapping("{id}/active")
    public ResponseEntity<CoursePatchResponse> toggleStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.toggleStatus(id));
    }

}