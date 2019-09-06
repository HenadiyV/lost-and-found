package com.example.advt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *12.08.2019
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(){
        return "index";
    }
    @GetMapping("/about")
    public String about(){
        return "page-about-us";
    }
    @GetMapping("/contact")
    public String contact(){
        return "page-contact-us";
    }
    @GetMapping("/coming")
    public String coming(){
        return "page-coming-us";
    }
    @GetMapping("/events")
    public String events(){
        return "page-events";
    }
    @GetMapping("/faq")
    public String faq(){
        return "page-faq";
    }
    @GetMapping("/reset-password")
    public String resetPassword(){
        return "page-password-reset";
    }
    @GetMapping("/homepage-sample")
    public String homepageSample(){
        return "page-homepage-sample";
    }

    @GetMapping("/product-details")
    public String productDetails(){
        return "page-product-details";
    }

    @GetMapping("/terms-privacy")
    public String termsPrivacy(){
        return "page-terms-privacy";
    }

    @GetMapping("/piople")
    public String piople(){
        return "page-piople";
    }

    @GetMapping("/add-advt")
    public String addAdvt(){
        return "page-advt";
    }
    @GetMapping("/test")
    public String listAdvt(){
        return "testVue";
    }
}
