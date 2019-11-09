package com.example.advt.controller;

import com.example.advt.domain.AdminPost;
import com.example.advt.domain.MessageUser;
import com.example.advt.domain.User;
import com.example.advt.repos.AdminPostRepository;
import com.example.advt.repos.MessageRepository;
import com.example.advt.repos.UserRepository;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *16.09.2019
 */

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AdminPostRepository adminPostRepository;

    @RequestMapping("/unauthenticated")
    public String unauthenticated() {
        return "redirect:/?error=true";
    }
    @RequestMapping({ "/user", "/me" })
    public Map<String, String> user(Principal principal) {
        if(principal!=null){
            User userAuth=userRepository.findByName( principal.getName());
           List<MessageUser> messageUser=messageRepository.findByIdToUserAndActive(userAuth.getId(),true);
           List<AdminPost> adminPosts=adminPostRepository.findByActive(true);
           String countMessage=String.valueOf(messageUser.size());
           String countPosts=String.valueOf(adminPosts.size());
           String userId=String.valueOf(userAuth.getId());

        Map<String, String> map = new LinkedHashMap<>();
        if(userService.userAdmin()){
                map.put("adm", "1");
            }
        map.put("name", principal.getName());
            map.put("countMessage", countMessage);
            map.put("countPosts", countPosts);
            map.put("userId", userId);
        return map;
        } else return null;
    }
}
