package com.joaocapobiango.coursesbackend.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoursePatchRequest {

    private String name;

    private String category;

}