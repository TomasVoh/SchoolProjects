package com.example.schoolProjects.Dto;

import com.example.schoolProjects.Model.Student;
import com.example.schoolProjects.Model.Subject;
import com.example.schoolProjects.Model.Teacher;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectDto {
    private long id;
    private String name;
    private Student student;
    private Teacher teacher;
    private Subject subject;
}
