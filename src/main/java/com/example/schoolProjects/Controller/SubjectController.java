package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Model.Subject;
import com.example.schoolProjects.Service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
public class SubjectController {
    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subject")
    public String showAllSubjects(Model model) {
        List<Subject> subjectList = subjectService.getAll();
        model.addAttribute("subjects", subjectList);
        return "subject";
    }

    @GetMapping("/subject/new")
    public String createSubjectForm(Model model) {
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject-create";
    }

    @PostMapping("/subject/new")
    public String createSubject(@ModelAttribute Subject subject) {
        subjectService.createSubject(subject);
        return "redirect:/subject";
    }

    @GetMapping("/subject/import/excel")
    public String uploadSubjectDataForm() {
        return "subject-import";
    }

    @PostMapping("/subject/import/excel")
    public String uploadSubjectData(@RequestParam("file") MultipartFile file) {
        subjectService.getSubjectsFromExcel(file);
        return "redirect:/subject";
    }
}
