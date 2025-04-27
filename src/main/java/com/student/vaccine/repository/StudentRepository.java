package com.student.vaccine.repository;

import com.student.vaccine.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT COUNT(s) FROM Student s")
    long countAll();

    @Query("SELECT COUNT(s) FROM Student s WHERE s.isVaccinated = true")
    long countVaccinated();
}
