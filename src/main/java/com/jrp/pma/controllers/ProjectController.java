package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("allEmployees", employeeRepository.findAll());
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String saveProject(Project project, Model model) {
        projectRepository.save(project);


        //use a redirect to prevent duplicate submission
        return "redirect:/projects";
    }

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }


}
