package com.joaocapobiango.coursesbackend.course.dto;

import com.joaocapobiango.coursesbackend.course.entity.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

    private Long id;

    private String name;

    private String category;

    private String status;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}