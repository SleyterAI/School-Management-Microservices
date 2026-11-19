package com.sai.enrollment_service.Messaging;

import com.sai.enrollment_service.Event.EnrollmentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentEventProducer {

    private static final String TOPIC = "enrollment-created";

    private final KafkaTemplate<String, EnrollmentCreatedEvent> kafkaTemplate;

    public void publish(EnrollmentCreatedEvent event) {
        kafkaTemplate.send(
                TOPIC,
                event.enrollmentId().toString(),
                event
        );
    }
}
