package com.sai.student_service.Service.Interface;

import com.sai.student_service.Entity.Student;

import java.util.List;

public interface IStudentService {

    Student createStudent(Student student);

    List<Student> getAllStudent();
    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
