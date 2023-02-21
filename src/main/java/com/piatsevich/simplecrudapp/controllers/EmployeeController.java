package com.piatsevich.simplecrudapp.controllers;

import com.piatsevich.simplecrudapp.models.Employee;
import com.piatsevich.simplecrudapp.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("employee", employeeService.getAll());
        return "employee/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/show";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute Employee employee) {
        return "employee/new";
    }

    @PostMapping()
    public String create(@ModelAttribute Employee employee) {
        employeeService.create(employee);
        return "redirect:/employee";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("employee") Employee employee, @PathVariable("id") int id) {
        employeeService.update(employee);
        return "redirect:/employee";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/employee";
    }
}
