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
    private String phone;
    private String email;
    private boolean active;
    private String nameSocial;
    private int advt_count;

    public User getUser(String name) {
       return userRepository.findByName(name);
    }

    public UserDAO(Long id, String name, String phone, String email, boolean active, String nameSocial, int advt_count) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.active = active;
        this.nameSocial = nameSocial;
        this.advt_count = advt_count;
    }

    public UserDAO() {
    }
}
