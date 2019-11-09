//package com.example.advt.controller;
//
//import com.example.advt.domain.Subcategory;
//import com.example.advt.domain.Views;
//import com.example.advt.dto.SubCategoryDto;
//import com.example.advt.repos.SubcategoryRepository;
//import com.example.advt.repos.SubcategoryRepositoryTest;
//import com.fasterxml.jackson.annotation.JsonView;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///*
// *@autor Hennadiy Voroboiv
// *@email henadiyv@gmail.com
// *11.10.2019
// */
//@RestController
//@RequestMapping("sub")
//public class SubCategoryRestController {
//    private final SubcategoryRepositoryTest subcategoryRepositoryTest;
//
//    @Autowired
//    public SubCategoryRestController(SubcategoryRepositoryTest subcategoryRepositoryTest) {
//        this.subcategoryRepositoryTest = subcategoryRepositoryTest;
//    }
//
//
//    @GetMapping
//    //@JsonView(Views.IdName.class)
//    public List<Subcategory> listSubcategory(){
//        SubCategoryDto ST=new SubCategoryDto((List<Subcategory>)subcategoryRepositoryTest.findAll());
//        return ST.getSubcategoryList();
//
//    }
//    @GetMapping("{id}")
//    @JsonView(Views.FullMessage.class)
//    public Subcategory getOne(@PathVariable("id") Subcategory subcategory) {
//        return subcategory;
//    }
//
//    @PostMapping
//    public Subcategory create(@RequestBody Subcategory subcategory) {
//       // subcategory.setCreationDate(LocalDateTime.now());
//        return subcategoryRepositoryTest.save(subcategory);
//    }
//
//    @PutMapping("{id}")
//    public Subcategory update(
//            @PathVariable("id") Subcategory subcategoryFromDb,
//            @RequestBody Subcategory subcategory
//    ) {
//        BeanUtils.copyProperties(subcategory, subcategoryFromDb, "id");
//
//        return subcategoryRepositoryTest.save(subcategoryFromDb);
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") Subcategory subcategory) {
//        subcategoryRepositoryTest.delete(subcategory);
//    }
//}
