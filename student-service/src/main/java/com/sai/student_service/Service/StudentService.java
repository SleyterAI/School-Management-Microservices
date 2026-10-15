package com.sai.student_service.Service;

import com.sai.student_service.Dto.StudentRequest;
import com.sai.student_service.Dto.StudentResponse;
import com.sai.student_service.Entity.Student;
import com.sai.student_service.Mapper.StudentMapper;
import com.sai.student_service.Repository.StudentRepository;
import com.sai.student_service.Service.Interface.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse createStudent(StudentRequest request) {
        Student student = studentMapper.toEntity(request);
        Student saved = studentRepository.save(student);
        return studentMapper.toResponse(saved);
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow();
        return studentMapper.toResponse(student);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest student) {
        studentRepository.findById(id)
                .orElseThrow();

        Student update = Student.builder()
                .name(student.getName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .age(student.getAge())
                .grade(student.getGrade())
                .build();
        return studentMapper.toResponse(studentRepository.save(update));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student doesnt exist");
        }
        studentRepository.deleteById(id);
    }
}
