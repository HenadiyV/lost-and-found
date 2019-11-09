package com.example.advt.repos;

import com.example.advt.domain.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubcategoryRepository  extends JpaRepository<Subcategory,Integer> {

    Iterable<Subcategory> findAllById(int id);
    List<Subcategory> findByCategory_Id(int id);
    Iterable<Subcategory> findByCategoryId(Integer id);
    Subcategory findById(int id);
    Subcategory findByCategoryIdAndName(int id,String name);
    List<Subcategory> findAll();


}
