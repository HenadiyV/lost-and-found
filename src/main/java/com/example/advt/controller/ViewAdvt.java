package com.example.advt.controller;

import com.example.advt.dao.GuestMessage;
import com.example.advt.domain.Advt;
import com.example.advt.domain.User;
import com.example.advt.repos.AdvtRepository;
import com.example.advt.service.CustomUserDetail;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *16.09.2019
 */
@Controller
@RequestMapping("/view")
public class ViewAdvt {
    @Autowired
    private AdvtRepository advtRepository;
    @Autowired
    private UserService userService;

    @GetMapping("people")
    public String ViewPeople(Principal principal, Map<String, Object> model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        int rl = 0;
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            boolean bl = userService.userAdmin();
            if (bl) {
                rl = 2;
            } else rl = 1;
        }
        model.put("category", 1);
        model.put("us", rl);
        model.put("url", "page-people");
        model.put("lost", "Розшукується");
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(1, false, pageable);
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());

        Page<Advt> adverListFound = advtRepository.findByCategory_IdAndFound(1, true, pageable);
        model.put("adverListFound", adverListFound);
        model.put("numFound", adverListFound.getTotalElements());
        return "page-people";
    }

    @GetMapping("animal")
    public String ViewAnimal(Principal principal, Map<String, Object> model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = 0;
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            boolean bl = userService.userAdmin();
            if (bl) {
                rl = 2;
            } else rl = 1;
        }
        model.put("url", "page-animal");
        model.put("category", 2);

        model.put("lost", "Зник");
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(2, false, pageable);
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());

        Page<Advt> adverListFound = advtRepository.findByCategory_IdAndFound(2, true, pageable);
        model.put("adverListFound", adverListFound);
        model.put("numFound", adverListFound.getTotalElements());
        return "page-animal";
    }

    @GetMapping("thing")
    public String ViewThing(Principal principal, Map<String, Object> model,
                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = 0;
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            boolean bl = userService.userAdmin();
            if (bl) {
                rl = 2;
            } else rl = 1;
        }
        model.put("url", "page-thing");
        model.put("category", 3);

        model.put("lost", "Загубленно");
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(3, false, pageable);
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());

        Page<Advt> adverListFound = advtRepository.findByCategory_IdAndFound(3, true, pageable);
        model.put("adverListFound", adverListFound);
        model.put("numFound", adverListFound.getTotalElements());
        return "page-thing";
    }

    private static final int MESSAGE_LENGTH = 100;

    @RequestMapping(value = "/message1", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody
    String message1(@RequestParam(value = "textMessage") String textMessage) {
        int a = MESSAGE_LENGTH - textMessage.length();

        if (a > 0) {

            return String.valueOf(a) + " ";
        }
        return "";
    }

    @GetMapping("/message")

    public String UserMessage() {

        return "message";
    }

    @PostMapping("/add_message")
    public String addMessage(@Valid GuestMessage guestMessage, BindingResult bindingResult, Model model) {

        return "message";
    }

    @PostMapping("/messageUser")
    public String ReturnUserId(@RequestParam(required = false, value = "idUser") int id, @RequestParam("idAdver") int idAdver, Model model) {
        if (id > 0)
            model.addAttribute("userId", id);
        else {
            model.addAttribute("userId", 0);
        }
        model.addAttribute("idAdver", idAdver);
        return "message";
    }
}
