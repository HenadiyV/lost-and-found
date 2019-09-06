package com.example.advt.domain;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *24.08.2019
 */

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

//@Entity
//@Table(name = "user_social")
//@Data
public class UserSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long idUser;
    private String idGoogle;
    private String nameGoogle;
    private String userpicGoogle;
    private String emailGoogle;
    private String genderGoogle;
    private String localeGoogle;

    private LocalDateTime lastVisit;

//    @OneToMany(mappedBy="usr",targetEntity=Advt.class,cascade = CascadeType.ALL)
//    private Set<Advt> advers;

}