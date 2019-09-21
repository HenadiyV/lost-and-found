package com.example.advt.dao;

import javax.validation.constraints.NotEmpty;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *07.08.2019
 */
public class GuestMessage {
    private int idAdver;
    @NotEmpty(message="Не вказано текст повідомленя .")
    private String textMessage;
    private boolean capcha;

    public int getId() {
        return idAdver;
    }

    public void setId(int id) {
        this.idAdver = id;
    }

    public String getText() {
        return textMessage;
    }

    public void setText(String text) {
        this.textMessage = text;
    }

    public boolean isCapcha() {
        return capcha;
    }

    public void setCapcha(boolean capcha) {
        this.capcha = capcha;
    }
}
