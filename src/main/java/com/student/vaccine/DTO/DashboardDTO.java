package com.student.vaccine.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DashboardDTO {
    private long totalStudents;
    private long vaccinatedStudents;
    private double vaccinationPercentage;
    private List<DriveDTO> upcomingDrives;

    @Data
    public static class DriveDTO {
        private String vaccineName;
        private LocalDate scheduledDate;
        private String classesApplicable;
        private int dosesAvailable;
    }
}