package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Model.Teacher;
import com.example.schoolProjects.Service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TeacherController {

    private TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping("/teacher")
    public String getAllTeachers(Model model) {
        List<Teacher> teacherList = service.getAll();
        model.addAttribute("teachers", teacherList);
        return "teacher";
    }
}
