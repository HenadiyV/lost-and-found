package com.example.advt.dto;

import com.example.advt.domain.User;
import lombok.Data;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *05.11.2019
 */
@Data
public class UserRolyDTO {
    private Long idUser;
    private String email;
    private String name;
    private boolean active;
    private boolean us;
    private boolean adm;
    private boolean mod;

    public UserRolyDTO() {
    }

    public UserRolyDTO(Long idUser, String email, String name, boolean active, boolean us, boolean adm, boolean mod) {
        this.idUser = idUser;
        this.email = email;
        this.name = name;
        this.active = active;
        this.us = us;
        this.adm = adm;
        this.mod = mod;
    }
}
