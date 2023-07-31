package com.example.schoolProjects.Service;


import com.example.schoolProjects.Dto.ProjectDto;
import com.example.schoolProjects.Mapper.ProjectMapper;
import com.example.schoolProjects.Model.Project;
import com.example.schoolProjects.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService{
    private ProjectRepository projectRepository;
    private ExcelImportService excelImportService;

    public ProjectService(ProjectRepository projectRepository, ExcelImportService excelImportService) {
        this.projectRepository = projectRepository;
        this.excelImportService = excelImportService;
    }

    public List<ProjectDto> getAll() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(ProjectMapper::mapToProjectDto).collect(Collectors.toList());
    }

    public ProjectDto getById(long id) {
        Project project = projectRepository.findById(id).get();
        return ProjectMapper.mapToProjectDto(project);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<ProjectDto> getProjectByStudentId(long id) {
        List<Project> projects = projectRepository.findProjectByStudentId(id);
        return projects.stream().map(ProjectMapper::mapToProjectDto).collect(Collectors.toList());
    }

    public List<ProjectDto> getProjectByTeacherId(long id) {
        List<Project> projects = projectRepository.findProjectByTeacherId(id);
        return projects.stream().map(ProjectMapper::mapToProjectDto).collect(Collectors.toList());
    }

    public void createProjectFromExcel(MultipartFile file) {
        if(excelImportService.isFileValidXlsx(file)) {
            try {
                List<Project> projects = excelImportService.getProjectData(file.getInputStream());
                projectRepository.saveAll(projects);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
