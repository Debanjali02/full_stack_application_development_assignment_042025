package com.student.vaccine.service;

import com.student.vaccine.entity.VaccinationDrive;
import com.student.vaccine.repository.VaccDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccinationDriveService {

    @Autowired
    private VaccDriveRepository driveRepository;

    public VaccinationDrive requestVaccinationDrive(VaccinationDrive drive) {
        drive.setIsApproved(false); // ensure drive is pending approval
        drive.setCreatedAt(LocalDateTime.now());
        return driveRepository.save(drive);
    }

    public VaccinationDrive approveDrive(Long id) {
        VaccinationDrive drive = driveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drive not found"));
        drive.setIsApproved(true);
        return driveRepository.save(drive);
    }

    public List<VaccinationDrive> getAllApprovedDrives() {
        return driveRepository.findByIsApprovedTrue();
    }

    public List<VaccinationDrive> getPendingApprovalDrives() {
        return driveRepository.findAll()
                .stream()
                .filter(d -> !Boolean.TRUE.equals(d.getIsApproved()))
                .collect(Collectors.toList());
    }

    public List<VaccinationDrive> getUpcomingDrives() {
        return driveRepository.findByIsCompletedFalse();
    }

    public Optional<VaccinationDrive> getDriveById(Long id) {
        return driveRepository.findById(id);
    }

    public VaccinationDrive updateDrive(Long id, VaccinationDrive updatedDrive) {
        return driveRepository.findById(id).map(existing -> {
            if (!Boolean.TRUE.equals(existing.getIsCompleted())) {
                existing.setScheduledDate(updatedDrive.getScheduledDate());
                existing.setDosesAvailable(updatedDrive.getDosesAvailable());
                existing.setVaccineName(updatedDrive.getVaccineName());
                existing.setClassesApplicable(updatedDrive.getClassesApplicable());
                return driveRepository.save(existing);
            } else {
                throw new RuntimeException("Cannot update a completed drive.");
            }
        }).orElseThrow(() -> new RuntimeException("Drive not found."));
    }
}