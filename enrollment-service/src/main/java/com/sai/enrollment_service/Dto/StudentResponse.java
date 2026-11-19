package com.sai.enrollment_service.Dto;

public record StudentResponse(
         Long id,
         String name,
         String lastName,
         String email,
         Integer age,
         String grade
) {}
