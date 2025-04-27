package com.student.vaccine.controller;

import com.student.vaccine.entity.VaccinationRecord;
import com.student.vaccine.service.VaccinationReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class VaccReportController {

    @Autowired
    private VaccinationReportService reportService;

    @GetMapping("/vaccination")
    public ResponseEntity<Page<VaccinationRecord>> getVaccinationReport(
            @RequestParam(defaultValue = "") String vaccine,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(reportService.getVaccinationReport(vaccine, page, size));
    }

    @GetMapping("/vaccination/download")
    public void downloadVaccinationReport(
            @RequestParam(defaultValue = "") String vaccine,
            HttpServletResponse response) throws IOException {

        List<VaccinationRecord> records = reportService.getAllRecords(vaccine);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=vaccination_report.xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Vaccination Report");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Student Name");
        header.createCell(1).setCellValue("Class");
        header.createCell(2).setCellValue("Vaccine");
        header.createCell(3).setCellValue("Vaccination Date");
        header.createCell(4).setCellValue("Vaccinated");

        int rowNum = 1;
        for (VaccinationRecord record : records) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(record.getStudent().getName());
            row.createCell(1).setCellValue(record.getStudent().getStudentClass());
            row.createCell(2).setCellValue(record.getVaccineName());
            row.createCell(3).setCellValue(record.getVaccinationDate().toString());
            row.createCell(4).setCellValue(record.isVaccinated() ? "Yes" : "No");
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
