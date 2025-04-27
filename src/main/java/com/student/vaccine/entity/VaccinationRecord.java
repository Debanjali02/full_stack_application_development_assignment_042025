package com.student.vaccine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vaccination_records", schema = "school_vaccination_portal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;

    @Column(name = "is_vaccinated")
    private boolean isVaccinated;
}
