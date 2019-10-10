package com.example.advt.dao;

import lombok.Data;

import java.util.Date;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *06.10.2019
 */
@Data
public class GuestMessageViewDAO {
    public Long idMessage;
    private String textMessage;
    private String contact;
    private boolean active;
    private Date datMessage;
    public Long idAdvt;
    private String textAdvt;
    private String cityAdvt;
    private String statusAdvt;
    private String subcategoryAdvt;
    private Date datAdvt;

    public GuestMessageViewDAO() {
    }

    public GuestMessageViewDAO(Long idMessage, String textMessage, String contact, boolean active, Date datMessage, Long idAdvt, String textAdvt, String cityAdvt, String statusAdvt, String subcategoryAdvt, Date datAdvt) {
        this.idMessage = idMessage;
        this.textMessage = textMessage;
        this.contact = contact;
        this.active = active;
        this.datMessage = datMessage;
        this.idAdvt = idAdvt;
        this.textAdvt = textAdvt;
        this.cityAdvt = cityAdvt;
        this.statusAdvt = statusAdvt;
        this.subcategoryAdvt = subcategoryAdvt;
        this.datAdvt = datAdvt;
    }
}
