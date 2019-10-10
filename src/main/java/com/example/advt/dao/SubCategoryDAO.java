package com.example.advt.dao;

import com.example.advt.domain.Subcategory;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *29.09.2019
 */
@Data
public class SubCategoryDAO {
    public int id;
    public String name;
public int category;

}
