package com.example.advt.domain;

import org.springframework.security.core.GrantedAuthority;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *09.06.2019
 */
public enum Role implements GrantedAuthority {
    USER,ADMIN,MODERATOR ;

    @Override
    public String getAuthority() {
        return name();
    }

    public String getRole() {
        return Role.values().toString();
    }
}
