package com.joaocapobiango.coursesbackend.course.controller;

import com.joaocapobiango.coursesbackend.course.dto.*;
import com.joaocapobiango.coursesbackend.course.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<CourseResponse> create(
            @RequestBody CoursePostRequest coursePostRequest,
            HttpServletRequest httpServletRequest) {
        var accountId = Long.valueOf(httpServletRequest.getAttribute("account_id").toString());
        coursePostRequest.setAccountId(accountId);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(coursePostRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseResponse>> get(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAll(name, category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> update(
            @PathVariable Long id,
            @RequestBody CoursePutRequest coursePutRequest,
            HttpServletRequest httpServletRequest) {
        var accountId = Long.valueOf(httpServletRequest.getAttribute("account_id").toString());
        return ResponseEntity.status(HttpStatus.OK).body(this.service.update(id, accountId, coursePutRequest));
    }

    @PatchMapping("{id}/active")
    public ResponseEntity<CourseResponse> toggleStatus(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest) {
        var accountId = Long.valueOf(httpServletRequest.getAttribute("account_id").toString());
        return ResponseEntity.status(HttpStatus.OK).body(this.service.toggleStatus(id, accountId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest) {
        var accountId = Long.valueOf(httpServletRequest.getAttribute("account_id").toString());
        this.service.delete(id, accountId);
        return ResponseEntity.noContent().build();
    }

}