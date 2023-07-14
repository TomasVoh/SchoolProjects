package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Model.Subject;
import com.example.schoolProjects.Model.Teacher;
import com.example.schoolProjects.Service.SubjectService;
import com.example.schoolProjects.Service.TeacherService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {

    private TeacherService service;

    private SubjectService subjectService;

    public TeacherController(TeacherService service, SubjectService subjectService) {
        this.service = service;
        this.subjectService = subjectService;
    }

    @GetMapping("/teacher")
    public String getAllTeachers(Model model) {
        List<Teacher> teacherList = service.getAll();
        model.addAttribute("teachers", teacherList);
        return "teacher";
    }

    @GetMapping("/teacher/new")
    public String createTeacherForm(Model model) {
        Teacher teacher = new Teacher();
        List<Subject> subjects = subjectService.getAll();
        model.addAttribute("teacher", teacher);
        model.addAttribute("subjects", subjects);
        return "teacher-create";
    }

    @PostMapping("/teacher/new")
    public String createTeacher(@ModelAttribute("teacher") Teacher teacher) {
        String[] degrees = teacher.getPrefix().split(",");
        if(degrees.length == 1) {
            teacher.setPrefix(degrees[0]);
        } else {
            teacher.setPrefix(degrees[0]);
            teacher.setSuffix(degrees[1]);
        }
        service.createTeacher(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/teacher/subject/{id}")
    public String getTeacherBySubjectId(@PathVariable("id") long id, Model model) {
        List<Teacher> teachers = service.findTeachersBySubject(id);
        model.addAttribute("teachers", teachers);
        return "teacher";
    }
}
