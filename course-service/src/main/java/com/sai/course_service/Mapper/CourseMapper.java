package com.sai.course_service.Mapper;

import com.sai.course_service.Dto.CourseRequest;
import com.sai.course_service.Dto.CourseResponse;
import com.sai.course_service.Entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toEntity(CourseRequest request) {
        return Course.builder()
                .name(request.name())
                .description(request.description())
                .teacherName(request.teacherName())
                .build();
    }

    public CourseResponse toResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .teacherName(course.getTeacherName())
                .build()
        ;
    }
}
