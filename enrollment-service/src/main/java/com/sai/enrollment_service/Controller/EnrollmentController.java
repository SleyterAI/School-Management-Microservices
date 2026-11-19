package com.sai.enrollment_service.Controller;

import com.sai.enrollment_service.Dto.Enrollment.EnrollmentRequest;
import com.sai.enrollment_service.Dto.Enrollment.EnrollmentResponse;
import com.sai.enrollment_service.Service.Impl.EnrollmentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentServiceImpl enrollmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentResponse create(
            @Valid @RequestBody EnrollmentRequest request) {
        return enrollmentService.createEnrollment(request);
    }

    @GetMapping("/{id}")
    public EnrollmentResponse findById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @GetMapping
    public List<EnrollmentResponse> findAll() {
        return enrollmentService.getAllEnrollment();
    }

    @PutMapping("/{id}/cancel")
    public EnrollmentResponse cancel(@PathVariable Long id) {

        return enrollmentService.cancelEnrollment(id);
    }
}
