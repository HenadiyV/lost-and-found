package com.example.advt.controller;

import com.example.advt.dao.AdminPostDAO;
import com.example.advt.domain.AdminPost;
import com.example.advt.domain.User;
import com.example.advt.repos.AdminPostRepository;
import com.example.advt.repos.UserRepository;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *12.08.2019
 */
@Controller
@RequestMapping("/")
public class IndexController {

@Autowired
private UserRepository userRepository;
@Autowired
private UserService userService;
@Autowired
private AdminPostRepository adminPostRepository;
   final boolean logout=false;
    @GetMapping
    public String index(@AuthenticationPrincipal User user, Model model){

        return "index";
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @GetMapping("about")
    public String about(){

        return "page-about-us";
    }
    @GetMapping("contact")
    public String contact(AdminPostDAO adminPost,Model model){
        model.addAttribute("adminPost", adminPost);
        return "page-contact-us";
    }

    @PostMapping("contact")
    public String addPost(@Valid AdminPostDAO adminPost, BindingResult bindingResult, Model model) throws ParseException {
        model.addAttribute("adminPost", adminPost);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            if (adminPost.getName().isEmpty()) {
                model.addAttribute("nameError", "Не вказано имя");
            }
            if (adminPost.getEmail().isEmpty()) {
                model.addAttribute("emailError", "Не вказано email");
            }
            if (adminPost.getTopic().isEmpty()) {
                model.addAttribute("topicError", "Не вказано тему");
            }
            if (adminPost.getMessageUser().isEmpty()) {
                model.addAttribute("contactMessageUserError", "Не вказано текст");
            }
            return "page-contact-us";
        }else{
            model.addAttribute("sendMessage", "Ваше повідомленя відправлено");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date sendDate = dateFormat.parse(dateFormat.format(new Date()));
            AdminPost adminPost1=new AdminPost(adminPost.getName(),adminPost.getEmail(),adminPost.getMessageUser(),adminPost.getTopic(),true,sendDate);
            adminPostRepository.save(adminPost1);
        }
              return "redirect:/";

    }
    @GetMapping("blocked{id}")
    public String blocked(@PathVariable(value = "id")User us, Model model){
        model.addAttribute("name",us.getName());
        model.addAttribute("email",us.getEmail());
        return "page-unban-us";
    }

    @PostMapping("blocked")
    public String blocked(@Valid AdminPostDAO adminPost, BindingResult bindingResult, Model model) throws ParseException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            if (adminPost.getName().isEmpty()) {
                model.addAttribute("nameError", "Не вказано имя");
            }
            if (adminPost.getEmail().isEmpty()) {
                model.addAttribute("emailError", "Не вказано email");
            }
            if (adminPost.getMessageUser().isEmpty()) {
                model.addAttribute("contactMessageUserError", "Не вказано текст");
            }
        }else{
            model.addAttribute("sendMessage", "Ваше повідомленя відправлено");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date sendDate = dateFormat.parse(dateFormat.format(new Date()));
            AdminPost adminPost1=new AdminPost(adminPost.getName(),adminPost.getEmail(),adminPost.getMessageUser(),adminPost.getTopic(),true,sendDate);
            adminPostRepository.save(adminPost1);
        }
        return "redirect:/";
    }
    @GetMapping("faq")
    public String faq(){

        return "page-faq";
    }
    @GetMapping("terms-privacy")
    public String terms(){

        return "page-terms-privacy";
    }

//    @GetMapping("/reset-password")
////    public String resetPassword(@AuthenticationPrincipal User user,Model model){
////        return "page-password-reset";
////    }
}
