package com.example.advt.service;


import com.example.advt.dao.UserDAO;
import com.example.advt.domain.Role;
import com.example.advt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import sun.plugin2.util.SystemUtil;

import java.util.HashSet;
import java.util.Set;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *20.07.2019
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
//    private SystemUtil logger;

    public CustomUserDetail loadUserByUsername(String name) throws UsernameNotFoundException, DataAccessException {
        // returns the get(0) of the user list obtained from the db
        User domainUser = userDAO.getUser(name);


        Set<Role> roles = domainUser.getRoles();
//        logger.debug("role of the user" + roles);

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//            logger.debug("role" + role + " role.getRole()" + (role.getRole()));
        }

        CustomUserDetail customUserDetail = new CustomUserDetail();
        customUserDetail.setUser(domainUser);
        customUserDetail.setAuthorities(authorities);

        return customUserDetail;

    }

}

