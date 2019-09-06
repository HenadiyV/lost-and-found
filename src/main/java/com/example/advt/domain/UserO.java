package com.example.advt.domain;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *24.08.2019
 */

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

//@Entity
//@Table(name = "usr")
//@Data
public class UserO {
    @Id
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String locale;
    private LocalDateTime lastVisit;

//    @OneToMany(mappedBy="usr",targetEntity=Advt.class,cascade = CascadeType.ALL)
//    private Set<Advt> advers;
}