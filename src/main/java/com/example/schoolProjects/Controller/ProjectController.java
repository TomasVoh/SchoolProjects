package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Dto.ProjectDto;
import com.example.schoolProjects.Model.Project;
import com.example.schoolProjects.Model.Student;
import com.example.schoolProjects.Model.Subject;
import com.example.schoolProjects.Model.Teacher;
import com.example.schoolProjects.Service.ProjectService;
import com.example.schoolProjects.Service.StudentService;
import com.example.schoolProjects.Service.SubjectService;
import com.example.schoolProjects.Service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjectController {
    private ProjectService projectService;
    private StudentService studentService;
    private SubjectService subjectService;
    private TeacherService teacherService;

    public ProjectController(ProjectService projectService, StudentService studentService, SubjectService subjectService, TeacherService teacherService) {
        this.projectService = projectService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @GetMapping("/home")
    public String showProjects(Model model) {
        List<ProjectDto> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "home";
    }

    @GetMapping("/project/new")
    public String createProjectForm(Model model) {
        Project project = new Project();
        List<Student> students = studentService.getAll();
        List<Subject> subjects = subjectService.getAll();
        List<Teacher> teachers = teacherService.getAll();
        model.addAttribute("project", project);
        model.addAttribute("teachers", teachers);
        model.addAttribute("subjects", subjects);
        model.addAttribute("students", students);
        return "project-create";
    }

    @PostMapping("/project/new")
    public String createProject(@ModelAttribute("project") Project project) {
        projectService.createProject(project);
        return "redirect:/home";
    }
}
