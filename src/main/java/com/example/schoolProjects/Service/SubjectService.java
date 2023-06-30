package com.example.schoolProjects.Service;

import com.example.schoolProjects.Model.Project;
import com.example.schoolProjects.Model.Subject;
import com.example.schoolProjects.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAll() {
        List<Subject> projects = subjectRepository.findAll();
        return projects;
    }

    public Subject getById(long id) {
        return subjectRepository.findById(id).get();
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
}
