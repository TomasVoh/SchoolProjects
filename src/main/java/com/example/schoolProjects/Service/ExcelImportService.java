package com.example.schoolProjects.Service;

import com.example.schoolProjects.Model.Subject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelImportService {

    public boolean isFileValidXlsx(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public List<Subject> getSubjectData(InputStream inputStream) {
        List<Subject> subjects = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Předměty");
            int rowIndex = 0;
            for(Row row : sheet) {
                if(rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Subject subject = new Subject();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> {
                            subject.setName(cell.getStringCellValue());
                        }
                        case 1 -> {
                            subject.setShortcut(cell.getStringCellValue());
                        }
                        default -> {

                        }
                    }
                    cellIndex++;
                }
                subjects.add(subject);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return subjects;
    }
}
