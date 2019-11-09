package com.example.advt.dto;


import com.example.advt.domain.City;

import java.util.ArrayList;
import java.util.List;

public class CityDto {
    private City Sd;
    List<City> cityList=new ArrayList<City>();

    public CityDto() {
    }
    public CityDto(List<City> ls) {
        if (ls.size()<1)return;
        for (City stat: ls
             ) {
            City temp=new City();
            temp.setId(stat.getId());
            temp.setName(stat.getName());
            cityList.add(temp);
        }
    }

    public List<City> getListCitys() {
        return cityList;
    }
}
