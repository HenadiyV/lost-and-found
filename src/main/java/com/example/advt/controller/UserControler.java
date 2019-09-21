package com.example.advt.controller;

import com.example.advt.domain.User;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *16.09.2019
 */

@RestController
public class UserControler {
    @Autowired
    private UserService userService;
//    @RequestMapping("/user")
//    public User user(Principal principal) {
//        User user =new User();
//        if(principal != null){
//            user = (User) userService.loadUserByUsername(principal.getName());
//            return user;
//        }
//       return null;
//    }
    @RequestMapping("/unauthenticated")
    public String unauthenticated() {
        return "redirect:/?error=true";
    }
    @RequestMapping({ "/user", "/me" })
    public Map<String, String> user(Principal principal) {
        if(principal!=null){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());

        return map;
        } else return null;
    }
}
