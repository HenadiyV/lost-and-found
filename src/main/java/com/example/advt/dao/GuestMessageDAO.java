package com.example.advt.dao;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *07.08.2019
 */
public class GuestMessageDAO implements Serializable {
    private Long idAdver;
    private Long userId;
    private String textMessage;
    private String contact;

    public GuestMessageDAO() {
    }


    public GuestMessageDAO(Long idAdver, Long userId, String textMessage, String contact) {
        this.idAdver = idAdver;
        this.userId = userId;
        this.textMessage = textMessage;
        this.contact = contact;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIdAdver() {
        return idAdver;
    }

    public void setIdAdver(Long idAdver) {
        this.idAdver = idAdver;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }



    public Long getId() {
        return idAdver;
    }

    public void setId(Long id) {
        this.idAdver = id;
    }

    public String getText() {
        return textMessage;
    }

    public void setText(String text) {
        this.textMessage = text;
    }

}
