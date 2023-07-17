package com.example.schoolProjects.Controller;

import com.example.schoolProjects.Dto.RegistrationDto;
import com.example.schoolProjects.Model.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/register")
    public String registerForm(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("register", registrationDto);
        return "register";
    }
}
