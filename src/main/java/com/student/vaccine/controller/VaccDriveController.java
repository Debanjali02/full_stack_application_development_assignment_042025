package com.student.vaccine.controller;

import com.student.vaccine.entity.VaccinationDrive;
import com.student.vaccine.service.VaccinationDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccination-drives")
public class VaccDriveController {

    @Autowired
    private VaccinationDriveService driveService;

    // Book a new vaccination drive (approval pending)
    @PostMapping("/request")
    public ResponseEntity<VaccinationDrive> requestDrive(@RequestBody VaccinationDrive drive) {
        return ResponseEntity.ok(driveService.requestVaccinationDrive(drive));
    }

    // Admin approval endpoint
    @PutMapping("/approve/{id}")
    public ResponseEntity<VaccinationDrive> approveDrive(@PathVariable Long id) {
        return ResponseEntity.ok(driveService.approveDrive(id));
    }

    // Get approved drives
    @GetMapping("/approved")
    public ResponseEntity<List<VaccinationDrive>> getApprovedDrives() {
        return ResponseEntity.ok(driveService.getAllApprovedDrives());
    }

    // Get pending approval drives
    @GetMapping("/pending")
    public ResponseEntity<List<VaccinationDrive>> getPendingDrives() {
        return ResponseEntity.ok(driveService.getPendingApprovalDrives());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<VaccinationDrive>> getUpcomingDrives() {
        return ResponseEntity.ok(driveService.getUpcomingDrives());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDrive(@PathVariable Long id, @RequestBody VaccinationDrive updatedDrive) {
        try {
            VaccinationDrive drive = driveService.updateDrive(id, updatedDrive);
            return ResponseEntity.ok(drive);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}