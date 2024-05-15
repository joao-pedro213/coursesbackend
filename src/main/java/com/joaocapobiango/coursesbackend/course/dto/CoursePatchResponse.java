package com.joaocapobiango.coursesbackend.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoursePatchResponse {

    private Long id;

    private String status;

    private OffsetDateTime updatedAt;

}