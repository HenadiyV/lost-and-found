package com.example.advt.dto;

import com.example.advt.domain.City;
import com.example.advt.domain.Subcategory;

import java.util.ArrayList;
import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *11.10.2019
 */
public class SubCategoryDto {
    List<Subcategory> subcategoryList=new ArrayList<Subcategory>();

    public SubCategoryDto(List<Subcategory> newSubcategoryList) {
        if(newSubcategoryList.size()<1){
            return ;
        }
        for(Subcategory subcat:newSubcategoryList){
            Subcategory sub=new Subcategory();
            sub.setId(subcat.getId());
            sub.setName(subcat.getName());
            sub.setCategory(subcat.getCategory());
            subcategoryList.add(sub);
        }

    }

    public List<Subcategory> getSubcategoryList() {
        return subcategoryList;
    }
}
