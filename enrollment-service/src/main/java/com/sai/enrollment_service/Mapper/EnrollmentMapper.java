package com.sai.enrollment_service.Mapper;

import com.sai.enrollment_service.Dto.Enrollment.EnrollmentResponse;
import com.sai.enrollment_service.Entity.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public EnrollmentResponse toResponse(Enrollment enrollment) {

        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getCourseId(),
                enrollment.getEnrollmentDate(),
                enrollment.getStatus()
        );
    }

}
