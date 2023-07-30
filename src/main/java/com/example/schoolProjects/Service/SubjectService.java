package com.example.schoolProjects.Service;

import com.example.schoolProjects.Model.Subject;
import com.example.schoolProjects.Repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    private ExcelImportService excelImportService;

    public SubjectService(SubjectRepository subjectRepository, ExcelImportService excelImportService) {
        this.subjectRepository = subjectRepository;
        this.excelImportService = excelImportService;
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

    public void saveCustomersToDb(MultipartFile file) {
        if(excelImportService.isFileValidXlsx(file)) {
            try {
                List<Subject> subjects = excelImportService.getSubjectData(file.getInputStream());
                subjectRepository.saveAll(subjects);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
