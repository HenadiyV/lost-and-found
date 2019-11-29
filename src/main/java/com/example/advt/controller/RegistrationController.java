package com.example.advt.controller;

import com.example.advt.domain.Role;
import com.example.advt.domain.User;
import com.example.advt.repos.UserRepository;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *13.08.2019
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String mylogin() {
        return "login";
    }

    @GetMapping("/register")
    public String register(User user) {
        return "page-register";
    }

    @PostMapping("/register")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

        model.addAttribute("user",user);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            if(user.getName().isEmpty()){
                model.addAttribute("userError","Поле имя неможе бути пустим!");
            }
            if(user.getEmail().isEmpty()){
                model.addAttribute("emailError","Поле email неможе бути пустим!");
            }

            if(user.getPassword().isEmpty()){
                model.addAttribute("passwordError","Поле пароль неможе бути пустим!");
            }
            return "page-register";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exists!");
            return "login";
        }

        return "redirect:/login";
    }
}
