package io.github.lemniscatex.webapp.controller;

import io.github.lemniscatex.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users")
    public String getAllBills(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin-users";
    }
}
