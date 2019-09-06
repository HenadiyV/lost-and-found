package com.example.advt.controller;

import com.example.advt.domain.User;
import com.example.advt.repos.UserRepository;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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

    //    @GetMapping("/page-register")
////    User user
//    public String registration()
//    {
//
//        return "page-register";
//    }
    @GetMapping("/password-reset")
    public String passwordReset() {
        return "page-password-reset";
    }

    @GetMapping("/register")
    public String register() {
        return "page-register";
    }

    @PostMapping("/register")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            return "page-register";
        }
//userRepository.save(user);
        return "redirect:/login";
    }
}