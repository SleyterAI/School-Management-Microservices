package com.sai.course_service.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CourseRequest(
        @NotBlank
        @Size(max = 100)
        String name,

        @Size(max = 500)
        String description,

        @NotBlank
        @Size(max = 150)
        String teacherName
) {}
