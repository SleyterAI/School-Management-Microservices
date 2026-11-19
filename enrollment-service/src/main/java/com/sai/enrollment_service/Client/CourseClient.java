package com.sai.enrollment_service.Client;

import com.sai.enrollment_service.Dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "course-service",
        url = "${course.service.url}"
)
public interface CourseClient {

    @GetMapping("/api/v1/course/{id}")
    CourseResponse findById(@PathVariable UUID id);
}