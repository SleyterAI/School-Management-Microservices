package com.sai.student_service.Service.Interface;

import com.sai.student_service.Dto.StudentRequest;
import com.sai.student_service.Dto.StudentResponse;
import com.sai.student_service.Entity.Student;

import java.util.List;

public interface IStudentService {

    StudentResponse createStudent(StudentRequest student);

    List<StudentResponse> getAllStudent();
    StudentResponse getStudentById(Long id);

    StudentResponse updateStudent(Long id, StudentRequest student);

    void deleteStudent(Long id);
}
