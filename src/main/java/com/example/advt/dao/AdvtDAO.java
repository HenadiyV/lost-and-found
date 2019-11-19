package com.example.advt.dao;

import com.example.advt.domain.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *14.09.2019
 */
@Data
public class AdvtDAO {

    private City city;
    private int category;
    private int subcategory;
@NotEmpty
    private String textAdvt;
    private String status;
   private String chracters;
    private String url;


    public AdvtDAO() {
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    public String getTextAdvt() {
        return textAdvt;
    }

    public void setTextAdvt(String textAdvt) {
        this.textAdvt = textAdvt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChracters() {
        return chracters;
    }

    public void setChracters(String chracters) {
        this.chracters = chracters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
