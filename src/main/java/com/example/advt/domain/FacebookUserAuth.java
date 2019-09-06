package com.example.advt.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import javax.persistence.Table;
/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *05.09.2019
 */
@Entity
@Table(name = "facebook_auth_user")
@Data
public class FacebookUserAuth {
    @Id
    private String id;
    private Long userId;
    private String name;
    private String photos;
    private String email;
    private String gender;

    private LocalDateTime lastVisit;
}
