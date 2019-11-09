package com.example.advt.controller;

import com.example.advt.domain.City;
import com.example.advt.domain.Views;
import com.example.advt.dto.CityDto;
import com.example.advt.repos.CityRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *23.08.2019
 */
@RestController
@RequestMapping("admin/city")
public class CityController {
    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository){
        this.cityRepository=cityRepository;
    }

    @GetMapping
//  @JsonView({Views.FullMessage.class})
    public List<City> list(){
        CityDto ST=new CityDto((List<City>)cityRepository.findAll());
        return ST.getListCitys();
}
    @GetMapping("{id}")
    public City getOne(@PathVariable("id") City city) {
        return city;
    }

    @PostMapping
    public City create(@RequestBody City city) {

        return cityRepository.save(city);
    }

    @PutMapping("{id}")
    public City update(@PathVariable("id") City cityFromDb,
                       @RequestBody City city) {
        BeanUtils.copyProperties(city, cityFromDb, "id");

        return cityRepository.save(cityFromDb);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") City city) {
        if(city!=null){
       cityRepository.delete(city);
       return true;
        }
        return false;
    }
}
