package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Model.Student;
import com.example.schoolProjects.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public String getAll(Model model) {
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/student/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-create";
    }

    @PostMapping("/student/new")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentService.createStudent(student);
        return "redirect:/student";
    }
}
