package com.student.vaccine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vaccination_drives", schema = "school_vaccination_portal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    @Column(name = "doses_available")
    private int dosesAvailable;

    @Column(name = "classes_applicable")
    private String classesApplicable;

    @Column(name = "created_at", updatable = false)
    private java.time.LocalDateTime createdAt = java.time.LocalDateTime.now();
}
