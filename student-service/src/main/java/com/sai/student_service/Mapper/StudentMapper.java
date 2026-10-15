package com.sai.student_service.Mapper;

import com.sai.student_service.Dto.StudentRequest;
import com.sai.student_service.Dto.StudentResponse;
import com.sai.student_service.Entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    //request -> entity to DB
    public Student toEntity(StudentRequest request){
        return Student.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .age(request.getAge())
                .grade(request.getGrade())
                .build();
    }

    // entity from DB -> response
    public StudentResponse toResponse(Student student){
        return StudentResponse.builder()
                .name(student.getName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .age(student.getAge())
                .grade(student.getGrade())
                .build();
    }
}
