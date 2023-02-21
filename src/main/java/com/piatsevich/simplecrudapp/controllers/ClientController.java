package com.piatsevich.simplecrudapp.controllers;

import com.piatsevich.simplecrudapp.models.Client;
import com.piatsevich.simplecrudapp.models.Department;
import com.piatsevich.simplecrudapp.service.impl.ClientServiceImpl;
import com.piatsevich.simplecrudapp.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("client", clientService.getAll());
        return "client/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientService.getById(id));
        return "client/show";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute Client client) {
        return "client/new";
    }

    @PostMapping()
    public String create(@ModelAttribute Client client) {
        clientService.create(client);
        return "redirect:/client";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("client", clientService.getById(id));
        return "client/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("client") Client client, @PathVariable("id") int id) {
        clientService.update(client);
        return "redirect:/client";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        clientService.delete(id);
        return "redirect:/client";
    }
}

