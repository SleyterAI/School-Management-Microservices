package com.sai.student_service.Repository;

import com.sai.student_service.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>  {
}
