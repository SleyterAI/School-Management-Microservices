package com.sai.student_service.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private String grade;
}
