package com.student.vaccine.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students", schema = "school_vaccination_portal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "class")
    private String studentClass;

    @Column(name = "is_vaccinated")
    private Boolean isVaccinated = false;

    @Column(name = "created_at", updatable = false)
    private java.time.LocalDateTime createdAt = java.time.LocalDateTime.now();
}
