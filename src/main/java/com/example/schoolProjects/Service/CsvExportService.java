package com.example.schoolProjects.Service;

import com.example.schoolProjects.Dto.ProjectDto;
import com.example.schoolProjects.Model.Project;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@Data
public class CsvExportService {
    private ProjectService projectService;

    public CsvExportService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void writeProjects(Writer writer) {
        List<ProjectDto> projects = projectService.getAll();
        try {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            csvPrinter.printRecord("ID", "název", "student", "učitel", "předmět");
            for(ProjectDto projectDto : projects) {
                csvPrinter.printRecord(projectDto.getId(), projectDto.getName(), projectDto.getStudent().getName(), projectDto.getTeacher().getName(), projectDto.getSubject().getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
