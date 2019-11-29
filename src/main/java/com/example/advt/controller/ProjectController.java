package com.example.advt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *08.11.2019
 */
@Controller
public class ProjectController {
    @GetMapping("/about-project")
    public String aboutProject() {

        return "page-faq-project";
    }

    @GetMapping("/about-project/user")
    public String aboutProjectUser(Model model) {
        model.addAttribute("us", 1);
        return "page-faq-project";
    }

    @GetMapping("/about-project/admin")
    public String aboutProjectAdmin(Model model) {
        model.addAttribute("adm", 1);
        return "page-faq-project";
    }

    @GetMapping("/about-project/guest")
    public String aboutProjectGuest(Model model) {
        model.addAttribute("guest", 1);
        return "page-faq-project";
    }

    @GetMapping("/about-project/diagram")
    public String aboutProjectDiagram(Model model) {
        model.addAttribute("diagram", 1);
        return "page-faq-project";
    }

    @GetMapping("/about-project/error-page")
    public String aboutErrorPage(Model model) {
        model.addAttribute("err", 1);
        return "page-faq-project";
    }
}
