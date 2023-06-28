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
    private StudentRepository studentRepository;
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;

    public ProjectService(ProjectRepository projectRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<ProjectDto> getAll() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(ProjectMapper::mapToProjectDto).collect(Collectors.toList());
    }

    public ProjectDto getById(long id) {
        Project project = projectRepository.findById(id).get();
        return ProjectMapper.mapToProjectDto(project);
    }
}
