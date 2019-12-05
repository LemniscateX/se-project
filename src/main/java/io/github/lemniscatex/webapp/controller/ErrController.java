package io.github.lemniscatex.webapp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrController implements ErrorController {
    @RequestMapping("/err")
    public String handleError() {
        return "error";
    }

    @RequestMapping("/error")
    public String error() {
        return "redirect:/err";
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
