package com.piatsevich.simplecrudapp.controllers;

import com.piatsevich.simplecrudapp.models.Department;
import com.piatsevich.simplecrudapp.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("department", departmentService.getAll());
        return "department/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        return "department/show";
    }

    @GetMapping("/new")
    public String newDepartment(@ModelAttribute Department department) {
        return "department/new";
    }

    @PostMapping()
    public String create(@ModelAttribute Department department) {
        departmentService.create(department);
        return "redirect:/department";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("department", departmentService.getById(id));
        return "department/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("department") Department department, @PathVariable("id") int id) {
        departmentService.update(department);
        return "redirect:/department";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        departmentService.delete(id);
        return "redirect:/department";
    }
}
