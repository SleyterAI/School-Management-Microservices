package com.sai.enrollment_service.Repository;

import com.sai.enrollment_service.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentIdAndCourseId(
            Long studentId,
            UUID courseId
    );
}
