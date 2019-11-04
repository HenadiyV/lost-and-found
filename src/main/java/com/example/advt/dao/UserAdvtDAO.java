package com.example.advt.dao;




import com.example.advt.domain.Advt;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class UserAdvtDAO {
    private Long idUser;
    private Long idAdvt;
    private String text;
    private boolean activ;
    private String photo;
    private String status;
    private String characters;
    private Date dat;
private String city;
private String subcategory;

    public UserAdvtDAO(Long idUser, Long idAdvt, String text, boolean activ, String photo, String status, String characters, Date dat, String city, String subcategory) {
        this.idUser = idUser;
        this.idAdvt = idAdvt;
        this.text = text;
        this.activ = activ;
        this.photo = photo;
        this.status = status;
        this.characters = characters;
        this.dat = dat;
        this.city = city;
        this.subcategory = subcategory;
    }

}
