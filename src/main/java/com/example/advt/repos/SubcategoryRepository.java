package com.example.advt.repos;

import com.example.advt.domain.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepository  extends JpaRepository<Subcategory,Integer> {
    List<Subcategory> findByCategoryId(int id);
}
