package com.example.advt.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *05.09.2019
 */
@Entity
@Table(name = "google_auth_user")
@Data
public class GoogleUserAuth {
    @Id
    private String id;
    private Long userId;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String locale;
    private LocalDateTime lastVisit;
}
