package com.sai.enrollment_service.Service;

import com.sai.enrollment_service.Dto.Enrollment.EnrollmentRequest;
import com.sai.enrollment_service.Dto.Enrollment.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(EnrollmentRequest request);

    EnrollmentResponse getEnrollmentById(Long id);

    List<EnrollmentResponse> getAllEnrollment();

    EnrollmentResponse cancelEnrollment(Long id);
}
