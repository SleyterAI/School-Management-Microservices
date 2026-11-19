package com.sai.enrollment_service.Dto.Enrollment;

import com.sai.enrollment_service.Enum.EnrollmentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record EnrollmentResponse(

        Long id,
        Long studentId,
        UUID courseId,
        LocalDateTime enrollmentDate,
        EnrollmentStatus status

) {}
