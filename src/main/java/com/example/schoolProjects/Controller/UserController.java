package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Dto.RegistrationDto;
import com.example.schoolProjects.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("register", registrationDto);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("register") RegistrationDto registrationDto) {
        userService.saveUser(registrationDto);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/login";
    }
}
