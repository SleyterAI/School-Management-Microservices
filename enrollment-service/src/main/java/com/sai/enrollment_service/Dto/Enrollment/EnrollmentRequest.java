package com.sai.enrollment_service.Dto.Enrollment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record EnrollmentRequest(

        @NotNull(message = "Student ID is required")
        @Positive(message = "Student ID must be positive")
        Long studentId,

        @NotNull(message = "Course ID, uuid is required")
        UUID courseId

) {}
