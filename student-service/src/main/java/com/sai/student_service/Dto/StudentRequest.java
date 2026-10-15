package com.sai.student_service.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {

    @NotBlank(message = "name cant be blank")
    private String name;

    @NotBlank(message = "lastname cant be blank")
    private String lastName;

    @Email(message = "Invalid format for email")
    @NotBlank(message = "email cant be blank")
    private String email;

    @Min(value = 6, message = "min 5")
    @Max(value = 16, message = "max 16")
    @NotNull
    private int age;

    @NotBlank(message = "Grade cant be blank")
    private String grade;
}
