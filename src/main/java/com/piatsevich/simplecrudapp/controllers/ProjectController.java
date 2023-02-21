package com.piatsevich.simplecrudapp.controllers;

import com.piatsevich.simplecrudapp.models.Project;
import com.piatsevich.simplecrudapp.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("project", projectService.getAll());
        return "project/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        return "project/show";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute Project project) {
        return "project/new";
    }

    @PostMapping()
    public String create(@ModelAttribute Project project) {
        projectService.create(project);
        return "redirect:/project";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("project", projectService.getById(id));
        return "project/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("project") Project project, @PathVariable("id") int id) {
        projectService.update(project);
        return "redirect:/project";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        projectService.delete(id);
        return "redirect:/project";
    }
}
