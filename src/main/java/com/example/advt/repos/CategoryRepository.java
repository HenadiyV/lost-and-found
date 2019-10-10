package com.example.advt.repos;

import com.example.advt.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findById(int id);
}
