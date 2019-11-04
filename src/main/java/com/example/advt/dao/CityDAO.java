package com.example.advt.dao;

import java.io.Serializable;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *17.10.2019
 */
public class CityDAO implements Serializable {
    private int idCity;
    private String nameCity;

    public CityDAO() {
    }

    public CityDAO(int idCity, String nameCity) {
        this.idCity = idCity;
        this.nameCity = nameCity;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
