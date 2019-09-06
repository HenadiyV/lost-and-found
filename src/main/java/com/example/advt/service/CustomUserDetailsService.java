package com.example.advt.service;


import com.example.advt.domain.User;
import com.example.advt.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *20.07.2019
 */
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    UserRepository users;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails loadedUser;
//
//        try {
//            User client = users.findByName(username);
//            loadedUser = new org.springframework.security.core.userdetails.User(
//                    client.getUsername(), client.getPassword(),
//                    client.getRoles());
//        } catch (Exception repositoryProblem) {
//            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
//        }
//        return loadedUser;
//    }
//}
