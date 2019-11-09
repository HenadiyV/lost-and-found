package com.example.advt.controller;


import com.example.advt.domain.Category;
import com.example.advt.dto.CategoryDto;
import com.example.advt.repos.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *11.10.2019
 */
@RestController
@RequestMapping("admin/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    @GetMapping
//  @JsonView({Views.FullMessage.class})
    public List<Category> list(){
        CategoryDto categoryList=new CategoryDto((List<Category>)categoryRepository.findAll());
        return categoryList.getCategoryList();
    }
    @GetMapping("{id}")
    public Category getOne(@PathVariable("id") Category category) {
        return category;
    }

    @PostMapping
    public Category create(@RequestBody Category category) {

        return categoryRepository.save(category);
    }

    @PutMapping("{id}")
    public Category update(@PathVariable("id") Category categoryFromDb,
                           @RequestBody Category category) {
        BeanUtils.copyProperties(category, categoryFromDb, "id");

        return categoryRepository.save(categoryFromDb);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") Category category) {
        categoryRepository.delete(category);
        return true;
    }
}
