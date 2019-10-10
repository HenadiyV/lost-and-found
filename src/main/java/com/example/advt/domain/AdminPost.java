package com.example.advt.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *03.10.2019
 */
@Entity
@Table(name = "posts")
@Data
public class AdminPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="text")
    private String messageUser;

    @Column(name="topic")
    private String topic;

    @Column(name="active")
    private boolean active;

    @Temporal(TemporalType.DATE)
    @Column(name="date")
    private Date dateMessage;

    public AdminPost() {
    }

    public AdminPost(String name, String email, String messageUser, String topic, boolean active, Date dateMessage) {
        this.name = name;
        this.email = email;
        this.messageUser = messageUser;
        this.topic = topic;
        this.active = active;
        this.dateMessage = dateMessage;
    }
}
