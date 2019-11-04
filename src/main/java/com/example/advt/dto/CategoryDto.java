package com.example.advt.dto;

import com.example.advt.domain.Category;
import com.example.advt.domain.Subcategory;

import java.util.ArrayList;
import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *11.10.2019
 */
public class CategoryDto {
    List<Category> categoryList=new ArrayList<Category>();

    public CategoryDto(List<Category> newCategoryList) {
        if(newCategoryList.size()<1){
            return ;
        }
        for(Category cat:newCategoryList){
           Category sub=new Category();
            sub.setId(cat.getId());
            sub.setName(cat.getName());

            categoryList.add(sub);
        }

    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
