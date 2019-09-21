package com.example.advt.controller;

import com.example.advt.domain.User;
import com.example.advt.repos.UserRepository;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
   final boolean logout=false;
    @GetMapping
    public String index(@AuthenticationPrincipal User user, Model model){
       // User user=new User();
      //  String name="";


//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof User) {
//            name=((User)principal).getName();
//        }
//if(principal.equals("anonymousUser")){data.put("profile","");}
//        else{
//        data.put("profile",principal);
//    model.addAttribute("logout_user","1");
//        }
//HashMap<Object ,Object> data= new HashMap<>();
//        if(authUser()!=null){
//            data=authUser();
//        model.addAttribute("frontendData",data);

   // }
//if(user!=null){
//        model.addAttribute("frontendData",user.getName());}else{
//  userService.authUser(model);}
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

//    @GetMapping("/logout")
//    public String logout(){
//        //userService.authUser(model);
//        return "redirect:/";
//    }

    @GetMapping("/about")
    public String about(@AuthenticationPrincipal User user,Model model){
        //userService.authUser(model);
        return "page-about-us";
    }
    @GetMapping("/contact")
    public String contact(@AuthenticationPrincipal User user,Model model){
       // userService.authUser(model);
        return "page-contact-us";
    }
    @GetMapping("/coming")
    public String coming(@AuthenticationPrincipal User user,Model model){
       // userService.authUser(model);
        return "page-coming-us";
    }
    @GetMapping("/events")
    public String events(@AuthenticationPrincipal User user,Model model){
       // userService.authUser(model);
        return "page-events";
    }
    @GetMapping("/faq")
    public String faq(@AuthenticationPrincipal User user,Model model){
       // userService.authUser(model);
        return "page-faq";
    }
    @GetMapping("/reset-password")
    public String resetPassword(@AuthenticationPrincipal User user,Model model){

       // userService.authUser(model);
        return "page-password-reset";
    }
    @GetMapping("/homepage-sample")
    public String homepageSample(@AuthenticationPrincipal User user,Model model){
      //  userService.authUser(model);
        return "page-homepage-sample";
    }

    @GetMapping("/product-details")
    public String productDetails(Model model){
       // userService.authUser(model);
        return "page-product-details";
    }

    @GetMapping("/terms-privacy")
    public String termsPrivacy(Model model){
      //  userService.authUser(model);
        return "page-terms-privacy";
    }

    @GetMapping("/piople")
    public String piople(Model model){
       // userService.authUser(model);
        return "page-people";
    }

//    @GetMapping("/advt")
//    public String addAdvt(Model model){
//        authUser(model);
//        return "page-advt";
//    }
    @GetMapping("/test")
    public String listAdvt(Model model){
        //authUser(model);
        return "testVue";
    }
//    @GetMapping("/animal")
//    public String listAnimal(Model model){
//        //authUser(model);
//        return "page_advt_animals";
//    }
//     private void authUser(Model model){
//         HashMap<Object ,Object> data= new HashMap<>();
//         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         if(principal.equals("anonymousUser")){ data.put("profile"," ");}
//        else{
//             data.put("profile",principal);
//             model.addAttribute("frontendData",data);
//         }
//     }
}
