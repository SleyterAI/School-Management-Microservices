package com.sai.enrollment_service.Dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CourseResponse(
        UUID id,
        String name,
        String description,
        String teacherName
) {}
