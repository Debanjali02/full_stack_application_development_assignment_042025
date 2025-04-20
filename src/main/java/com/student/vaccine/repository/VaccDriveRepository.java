package com.student.vaccine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccDriveRepository extends JpaRepository<com.student.vaccine.entity.VaccinationDrive, Long> {
    @Query("SELECT d FROM VaccinationDrive d WHERE d.scheduledDate BETWEEN CURRENT_DATE AND :endDate")
    List<com.student.vaccine.entity.VaccinationDrive> findUpcomingDrives(@Param("endDate") LocalDate endDate);
}

