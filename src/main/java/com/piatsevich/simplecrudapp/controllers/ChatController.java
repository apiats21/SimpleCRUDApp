package com.piatsevich.simplecrudapp.controllers;

import com.piatsevich.simplecrudapp.models.Chat;
import com.piatsevich.simplecrudapp.models.Employee;
import com.piatsevich.simplecrudapp.service.impl.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private final ChatServiceImpl chatService;

    @Autowired
    public ChatController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("chat", chatService.getAll());
        return "chat/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("chat", chatService.getById(id));
        return "chat/show";
    }

    @GetMapping("/new")
    public String newChat(@ModelAttribute Chat chat) {
        return "chat/new";
    }

    @PostMapping()
    public String create(@ModelAttribute Chat chat) {
        chatService.create(chat);
        return "redirect:/chat";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("chat", chatService.getById(id));
        return "chat/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("chat") Chat chat, @PathVariable("id") int id) {
        chatService.update(chat);
        return "redirect:/chat";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        chatService.delete(id);
        return "redirect:/chat";
    }
}
