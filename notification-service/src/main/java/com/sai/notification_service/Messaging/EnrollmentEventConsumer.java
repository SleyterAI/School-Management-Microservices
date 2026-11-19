package com.sai.notification_service.Messaging;

import com.sai.notification_service.Event.EnrollmentCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class EnrollmentEventConsumer {

    @KafkaListener(
            topics = "enrollment-created",
            groupId = "notification-group"
    )
    public void consume(EnrollmentCreatedEvent event) {

        log.info("New enrollment received!");

        log.info("Enrollment ID: {}", event.enrollmentId());
        log.info("Student ID: {}", event.studentId());
        log.info("Course ID: {}", event.courseId());

        // Aquí implementaremos el envío de la notificación.
    }
}
