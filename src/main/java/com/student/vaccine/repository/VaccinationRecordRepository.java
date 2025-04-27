package com.student.vaccine.repository;

import com.student.vaccine.entity.VaccinationRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
    Page<VaccinationRecord> findByVaccineNameContainingIgnoreCase(String vaccineName, Pageable pageable);
}
