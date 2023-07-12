package com.example.schoolProjects.Service;

import com.example.schoolProjects.Dto.ProjectDto;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
        createCell(row, 0, "Id");
        createCell(row, 1, "Jméno");
        createCell(row, 2, "Student");
        createCell(row, 3, "Učitel");
        createCell(row, 4, "Předmět");
        List<ProjectDto> prjs = projectService.getAll();
        int rowCount = 1;
        for(ProjectDto projectDto : prjs) {
            Row regularRow = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(regularRow, columnCount++, projectDto.getId());
            createCell(regularRow, columnCount++, projectDto.getName());
            createCell(regularRow, columnCount++, projectDto.getStudent().getName());
            createCell(regularRow, columnCount++, projectDto.getTeacher().getName());
            createCell(regularRow, columnCount, projectDto.getSubject().getShortcut());
        }
    }

    public void createCell(Row row, int columnCount, Object value) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        style.setFont(font);
        font.setFontHeight(14);
        cell.setCellStyle(style);
        if(value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if(value instanceof String) {
            cell.setCellValue((String) value);
        }
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
