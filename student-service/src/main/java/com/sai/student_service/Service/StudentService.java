package com.sai.student_service.Service;

import com.sai.student_service.Entity.Student;
import com.sai.student_service.Repository.StudentRepository;
import com.sai.student_service.Service.Interface.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student update = studentRepository.findById(id)
                .orElseThrow();

        update = Student.builder()
                .name(student.getName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .build();
        return studentRepository.save(update);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student doesnt exist");
        }
        studentRepository.deleteById(id);
    }
}
