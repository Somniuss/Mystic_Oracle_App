package com.somniuss.oracle.controller;

import com.somniuss.oracle.dto.WebUserDto;
import com.somniuss.oracle.entity.User;
import com.somniuss.oracle.mapper.WebUserMapper;
import com.somniuss.oracle.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final WebUserMapper webUserMapper;

    @Autowired
    public AuthController(UserService userService, WebUserMapper webUserMapper) {
        this.userService = userService;
        this.webUserMapper = webUserMapper;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") @Valid WebUserDto userDto,
                               BindingResult result,
                               Model model) {

        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);  
            return "register";
        }

        User user = webUserMapper.fromWebDto(userDto);
        userService.saveUser(user);

        return "redirect:/login";
    }
}
