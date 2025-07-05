package com.somniuss.oracle.controller;

import com.somniuss.oracle.dto.WebUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("userDto", new WebUserDto());  
        return "register";
    }

    @GetMapping("/main")
    public String showMainPage() {
        return "main";
    }
}
