package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Dto.StudentPage;
import com.example.schoolProjects.Model.Student;
import com.example.schoolProjects.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public String getAll(Model model,  @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum, @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        StudentPage students = studentService.getAllByPage(pageNum, pageSize);
        model.addAttribute("students", students.getContent());
        int[] pages = new int[(int) students.getAllPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i++;
        }
        model.addAttribute("pages", pages);
        return "student";
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
