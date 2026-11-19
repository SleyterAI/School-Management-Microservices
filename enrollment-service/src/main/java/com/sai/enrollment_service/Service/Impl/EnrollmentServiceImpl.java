package com.sai.enrollment_service.Service.Impl;

import com.sai.enrollment_service.Client.CourseClient;
import com.sai.enrollment_service.Client.StudentClient;
import com.sai.enrollment_service.Dto.CourseResponse;
import com.sai.enrollment_service.Dto.Enrollment.EnrollmentRequest;
import com.sai.enrollment_service.Dto.Enrollment.EnrollmentResponse;
import com.sai.enrollment_service.Dto.StudentResponse;
import com.sai.enrollment_service.Entity.Enrollment;
import com.sai.enrollment_service.Enum.EnrollmentStatus;
import com.sai.enrollment_service.Event.EnrollmentCreatedEvent;
import com.sai.enrollment_service.Exception.BusinessException;
import com.sai.enrollment_service.Mapper.EnrollmentMapper;
import com.sai.enrollment_service.Messaging.EnrollmentEventProducer;
import com.sai.enrollment_service.Repository.EnrollmentRepository;
import com.sai.enrollment_service.Service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentClient studentClient;
    private final CourseClient courseClient;
    private final EnrollmentMapper enrollmentMapper;
    private final EnrollmentEventProducer eventProducer;

    @Override
    @Transactional
    public EnrollmentResponse createEnrollment(EnrollmentRequest request) {
        // 1. Validar estudiante
        StudentResponse student =
                studentClient.findById(request.studentId());

        // 2. Validar curso
        CourseResponse course =
                courseClient.findById(request.courseId());

        // 3. Validar matrícula duplicada
        boolean exists = enrollmentRepository.existsByStudentIdAndCourseId(
                        student.id(),
                        course.id());
        if (exists) { throw new BusinessException(
                    "Student is already enrolled in this course");}

        // 4. Crear matrícula
        Enrollment enrollment = Enrollment.builder()
                .studentId(student.id())
                .courseId(course.id())
                .enrollmentDate(LocalDateTime.now())
                .status(EnrollmentStatus.ACTIVE)
                .build();

        // 5. Guardar matrícula en postgreSQL
        Enrollment saved = enrollmentRepository.save(enrollment);

        // 6. Crear evento
        EnrollmentCreatedEvent event = new EnrollmentCreatedEvent(
                saved.getId(),
                saved.getStudentId(),
                saved.getCourseId(),
                saved.getEnrollmentDate()
        );

        // 7. Publicar evento en Kafka
        eventProducer.publish(event);

        // 8. Convertir a DTO
        return enrollmentMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentResponse getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found: " + id));

        return enrollmentMapper.toResponse(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getAllEnrollment() {
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public EnrollmentResponse cancelEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found: " + id));

        if (enrollment.getStatus() == EnrollmentStatus.CANCELLED) {
            throw new IllegalStateException("Enrollment is already cancelled");
        }

        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        Enrollment updated = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponse(updated);
    }
}
