package com.student.vaccine.service;

import com.student.vaccine.DTO.DashboardDTO;
import com.student.vaccine.repository.StudentRepository;
import com.student.vaccine.repository.VaccDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    @Autowired
    private StudentRepository studentRepo;
    @Autowired private VaccDriveRepository driveRepo;

    public DashboardDTO getDashboardData() {
        long total = studentRepo.countAll();
        long vaccinated = studentRepo.countVaccinated();
        double percentage = total == 0 ? 0.0 : (vaccinated * 100.0) / total;

        LocalDate endDate = LocalDate.now().plusDays(30);
        List<com.student.vaccine.entity.VaccinationDrive> drives = driveRepo.findUpcomingDrives(endDate);

        List<DashboardDTO.DriveDTO> driveDTOs = drives.stream().map(d -> {
            DashboardDTO.DriveDTO dto = new DashboardDTO.DriveDTO();
            dto.setVaccineName(d.getVaccineName());
            dto.setScheduledDate(d.getScheduledDate());
            dto.setClassesApplicable(d.getClassesApplicable());
            dto.setDosesAvailable(d.getDosesAvailable());
            return dto;
        }).collect(Collectors.toList());

        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setTotalStudents(total);
        dashboard.setVaccinatedStudents(vaccinated);
        dashboard.setVaccinationPercentage(percentage);
        dashboard.setUpcomingDrives(driveDTOs);

        return dashboard;
    }
}
