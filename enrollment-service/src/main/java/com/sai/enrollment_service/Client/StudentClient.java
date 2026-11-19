package com.sai.enrollment_service.Client;

import com.sai.enrollment_service.Dto.StudentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "student-service",
        url = "${student.service.url}"
)
public interface StudentClient {

    @GetMapping("/api/v1/student/{id}")
    StudentResponse findById(@PathVariable Long id);
}
