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
//    @RequestMapping("/user")
//    public Principal user(Principal principal) {
//        //if(principal!=null){}
//        return principal;
//
//    }
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
    public String contact(AdminPostDAO adminPost,Map<String, Object> model){
//Map<String,String> list=new HashMap<String, String>();
//        list.put("Загальна","Загальна");
//        list.put("Сервіс","Сервіс");
//        list.put("Заказ","Заказ");
//        model.put("topic",list);
        return "page-contact-us";
    }
// @RequestParam (value="name")String name  , ("name")String name,@RequestParam("email")String email,@RequestParam("prependedInput")String prependedInput,@RequestParam("contact-messageUser")String messageUser
    @PostMapping("contact")
    public String addPost(@Valid AdminPostDAO adminPost, BindingResult bindingResult, Model model) throws ParseException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            if (adminPost.getName().isEmpty()) {
                model.addAttribute("nameError", "Не вказано имя");
            }
            if (adminPost.getEmail().isEmpty()) {
                model.addAttribute("emailError", "Не вказано email");
            }
            if (!adminPost.getTopic().equals("General")||adminPost.getTopic().equals("Services")||adminPost.getTopic().equals("Orders")) {
                model.addAttribute("topicError", "Не вказано тему");
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

// ,@RequestParam(value="topic")String topic       if(!adminPost.getName().isEmpty()&&!adminPost.getEmail().isEmpty()&& !adminPost.getContactMessageUser().isEmpty()&&!adminPost.getTopic().isEmpty()){
//            adminPost.setName(name);
//            AdminPost newAdminPost= new AdminPost(adminPost.getName(),
//                    adminPost.getEmail(),
//                    adminPost.getContactMessageUser(),
//                    adminPost.getTopic(),true);
//            adminPostRepository.save(adminPost);
 //       }
         return "page-contact-us";
        //return "redirect:/";
    }
    @GetMapping("/coming")
    public String coming(){

        return "page-coming-us";
    }
//    @GetMapping("admin")
//    public String events(){
//
//        return "admin-page";
//    }
    @GetMapping("faq")
    public String faq(){

        return "page-faq";
    }
    @GetMapping("/reset-password")
    public String resetPassword(@AuthenticationPrincipal User user,Model model){


        return "page-password-reset";
    }
    @GetMapping("/homepage-sample")
    public String homepageSample(){

        return "page-homepage-sample";
    }

    @GetMapping("/product-details")
    public String productDetails(Model model){

        return "page-product-details";
    }

    @GetMapping("/terms-privacy")
    public String termsPrivacy(Model model){

        return "page-terms-privacy";
    }

    @GetMapping("/piople")
    public String piople(Model model){

        return "page-people";
    }


    @GetMapping("/test")
    public String listAdvt(Model model){

        return "testVue";
    }

}
