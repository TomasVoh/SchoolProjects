package com.example.schoolProjects.Service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExcelExportService {
    private ProjectService projectService;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelExportService(ProjectService projectService) {
        this.projectService = projectService;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();
    }

    public void writeContent() {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Id");
        Cell nameCell = row.createCell(1);
        nameCell.setCellValue("Jméno");
        Cell studentCell = row.createCell(2);
        studentCell.setCellValue("Student");
        Cell teacherCell = row.createCell(3);
        teacherCell.setCellValue("Učitel");
        Cell subjectCell = row.createCell(4);
        subjectCell.setCellValue("Předmět");
    }

    public void export(HttpServletResponse response) {
        writeContent();
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            workbook.write(servletOutputStream);
            workbook.close();
            servletOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
