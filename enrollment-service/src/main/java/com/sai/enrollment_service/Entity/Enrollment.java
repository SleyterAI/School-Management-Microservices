package com.sai.enrollment_service.Entity;

import com.sai.enrollment_service.Enum.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "enrollment",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "course_id"})
            }
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "course_id", nullable = false)
    private UUID courseId;

    @Column(nullable = false)
    private LocalDateTime enrollmentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;
}
