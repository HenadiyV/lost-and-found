package com.example.advt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *08.11.2019
 */
@Controller
public class ProjectController {
    @GetMapping("/about-project")
    public String aboutProject(){

        return "about/page-faq-project";
    }
}
