package com.example.schoolProjects.Service;

import com.example.schoolProjects.Model.Teacher;
import com.example.schoolProjects.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers;
    }
}
