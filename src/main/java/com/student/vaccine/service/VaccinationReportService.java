package com.student.vaccine.service;

import com.student.vaccine.entity.VaccinationRecord;
import com.student.vaccine.repository.VaccinationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationReportService {

    @Autowired
    private VaccinationRecordRepository recordRepository;

    public Page<VaccinationRecord> getVaccinationReport(String vaccineFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("vaccinationDate").descending());
        if (vaccineFilter != null && !vaccineFilter.isEmpty()) {
            return recordRepository.findByVaccineNameContainingIgnoreCase(vaccineFilter, pageable);
        } else {
            return recordRepository.findAll(pageable);
        }
    }

    public List<VaccinationRecord> getAllRecords(String vaccineFilter) {
        return vaccineFilter == null || vaccineFilter.isEmpty()
                ? recordRepository.findAll()
                : recordRepository.findByVaccineNameContainingIgnoreCase(vaccineFilter, Pageable.unpaged()).getContent();
    }
}
