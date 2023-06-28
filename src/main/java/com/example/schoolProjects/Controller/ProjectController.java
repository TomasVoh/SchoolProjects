package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Dto.ProjectDto;
import com.example.schoolProjects.Model.Project;
import com.example.schoolProjects.Service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/home")
    public String showProjects(Model model) {
        List<ProjectDto> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "home";
    }
}
