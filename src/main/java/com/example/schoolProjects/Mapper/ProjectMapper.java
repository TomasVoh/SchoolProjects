package com.example.schoolProjects.Mapper;

import com.example.schoolProjects.Dto.ProjectDto;
import com.example.schoolProjects.Model.Project;

public class ProjectMapper {

    public static Project mapToEntityProject(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .subject(projectDto.getSubject())
                .teacher(projectDto.getTeacher())
                .student(projectDto.getStudent())
                .build();
    }

    public static ProjectDto mapToProjectDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .subject(project.getSubject())
                .teacher(project.getTeacher())
                .student(project.getStudent())
                .build();
    }
}
