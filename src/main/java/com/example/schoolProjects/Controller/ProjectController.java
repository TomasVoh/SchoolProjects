package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Dto.ProjectDto;
import com.example.schoolProjects.Model.Project;
import com.example.schoolProjects.Model.Student;
import com.example.schoolProjects.Model.Subject;
import com.example.schoolProjects.Model.Teacher;
import com.example.schoolProjects.Service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class ProjectController {
    private ProjectService projectService;
    private StudentService studentService;
    private SubjectService subjectService;
    private TeacherService teacherService;
    private CsvExportService csvExportService;
    private ExcelExportService excelExportService;

    public ProjectController(ProjectService projectService, StudentService studentService, SubjectService subjectService, TeacherService teacherService, CsvExportService csvExportService, ExcelExportService excelExportService) {
        this.projectService = projectService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
        this.csvExportService = csvExportService;
        this.excelExportService = excelExportService;
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

    @RequestMapping("/project/export/csv")
    public void getProjectsInCsv(HttpServletResponse res) throws IOException {
        res.setContentType("text/csv");
        String header = "Content-Disposition";
        String title = "projects " + new Date() + ".csv";
        res.addHeader(header, "attachment;filename=" + title);
        csvExportService.writeProjects(res.getWriter());
    }

    @RequestMapping("/project/export/xlsx")
    public void getProjectsInXlsx(HttpServletResponse res) {
        res.setContentType("application/octet-stream");
        String title = "projects " + new Date() + ".xlsx";
        res.addHeader("Content-Disposition", "attachment;filename=" + title);
        excelExportService.export(res);
    }

    @GetMapping("/project/student/{id}")
    public String getProjectByStudentId(@PathVariable("id") long id, Model model) {
        List<ProjectDto> projects = projectService.getByStudentId(id);
        model.addAttribute("projects", projects);
        return "home";
    }
}
