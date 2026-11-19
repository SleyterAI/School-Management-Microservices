package com.sai.course_service.Service;

import com.sai.course_service.Dto.CourseRequest;
import com.sai.course_service.Dto.CourseResponse;
import com.sai.course_service.Entity.Course;
import com.sai.course_service.Mapper.CourseMapper;
import com.sai.course_service.Repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    @Transactional
    public CourseResponse createCourse(CourseRequest request) {
        Course course = mapper.toEntity(request);
        Course savedCourse = repository.save(course);
        return mapper.toResponse(savedCourse);
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourse() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CourseResponse getCourseById(UUID id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                                "Curso no encontrado: " + id));
        return mapper.toResponse(course);
    }

    @Transactional
    public CourseResponse updateCourse(UUID id, CourseRequest request) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado: " + id));
        course.setName(request.name());
        course.setDescription(request.description());
        course.setTeacherName(request.teacherName());
        return mapper.toResponse(repository.save(course));
    }

    @Transactional
    public void deleteCourse(UUID id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Curso no encontrado: " + id);
        }
        repository.deleteById(id);
    }
}
