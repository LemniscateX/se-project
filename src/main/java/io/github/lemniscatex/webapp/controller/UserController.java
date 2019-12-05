package io.github.lemniscatex.webapp.controller;

import io.github.lemniscatex.webapp.model.User;
import io.github.lemniscatex.webapp.repository.UserRepository;
import io.github.lemniscatex.webapp.util.FileOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "all";
    }

    @PostMapping("/add")
    public String addNewUser(@RequestParam(name = "name") String name,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "files") MultipartFile[] files) {
        AtomicBoolean alreadyExists = new AtomicBoolean(false);
        for (User user : userRepository.findAll()) {
            if (user.name.equals(name) && user.email.equals(email)) {
                alreadyExists.set(true);
            }
        }
        if (!alreadyExists.get()) {
            User user = new User(name, email);
            userRepository.save(user);
            FileOperation.storeFile("users", user.id, files);
            user.filePath = "users/" + user.id.toString() + "/";
            userRepository.save(user);
        }
        return "redirect:/all";
    }

    @PostMapping("/deleteById")
    public String deleteById(@RequestParam(name = "id") Integer id) {
        FileOperation.deleteFile("users", id);
        userRepository.deleteByIdEquals(id);
        return "redirect:/all";
    }

    @PostMapping("/deleteByName")
    public String deleteByName(@RequestParam(name = "name") String name) {
        User[] users = userRepository.getUserByName(name);
        for (User user:users) {
            FileOperation.deleteFile("users", user.id);
        }
        userRepository.deleteByName(name);
        return "redirect:/all";
    }

    @PostMapping("/deleteByEmail")
    public String deleteByEmail(@RequestParam(name = "email") String email) {
        User[] users = userRepository.getUserByEmail(email);
        for (User user:users) {
            FileOperation.deleteFile("users", user.id);
        }
        userRepository.deleteByEmail(email);
        return "redirect:/all";
    }
}