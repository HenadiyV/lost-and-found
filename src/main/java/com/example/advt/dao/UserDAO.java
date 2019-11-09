package com.example.advt.dao;

import com.example.advt.domain.User;
import com.example.advt.repos.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *06.09.2019
 */
@Data
public class UserDAO   {
    @Autowired
    UserRepository userRepository;
    private Long id;
    private String name;
    private String password;
    private String email;
    private User user;

    public User getUser(String name) {
       return userRepository.findByName(name);
    }



}
