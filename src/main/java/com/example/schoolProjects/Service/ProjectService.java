package com.example.schoolProjects.Service;


import com.example.schoolProjects.Dto.ProjectDto;
import com.example.schoolProjects.Mapper.ProjectMapper;
import com.example.schoolProjects.Model.Project;
import com.example.schoolProjects.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService{
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
}
