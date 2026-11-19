package com.sai.notification_service.Event;

import java.time.LocalDateTime;
import java.util.UUID;

public record EnrollmentCreatedEvent(
        Long enrollmentId,
        Long studentId,
        UUID courseId,
        LocalDateTime enrollmentDate
) {}
