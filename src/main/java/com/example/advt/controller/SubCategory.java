package com.example.advt.controller;

import com.example.advt.dao.SubCategoryDAO;
import com.example.advt.domain.Category;
import com.example.advt.domain.Subcategory;
import com.example.advt.repos.CategoryRepository;
import com.example.advt.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *30.09.2019
 */
@Controller
@RequestMapping("subcategory")
public class SubCategory {
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
//    @GetMapping
//    public String listSubcategory(Map<String, Object> model){
//
//        return "page-admin";@RequestParam("namesub")String name,@RequestParam("idsub")int id
//    }
@RequestMapping(value="/edit",method=RequestMethod.POST)
    public @ResponseBody
    boolean editSubcategory(@RequestBody SubCategoryDAO subCategoryDAO){
    Subcategory oldSubcategory=subcategoryRepository.findById(subCategoryDAO.getId());
    oldSubcategory.setName(subCategoryDAO.getName());
 //  subcategoryRepository.save(oldSubcategory);
return true;
}
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public @ResponseBody
    boolean addSubcategory(@RequestBody SubCategoryDAO subCategoryDAO){
    if(subCategoryDAO.getCategory()>0&& subCategoryDAO.getName()!=""){
        Subcategory newSubcategory=new Subcategory();
        newSubcategory.setName(subCategoryDAO.getName());
        int i= subCategoryDAO.getCategory();
        Category cat=categoryRepository.findById(i);
        newSubcategory.setCategory(cat);
 // subcategoryRepository.save(newSubcategory);
        return true;
    }else
        return false;
    }
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody
    boolean deleteSubcategory(@RequestBody int id){
    if(id!=0){
        Subcategory oldSubcategory=subcategoryRepository.findById(id);
        oldSubcategory.setName("Not name");
        subcategoryRepository.save(oldSubcategory);
       // subcategoryRepository.delete(oldSubcategory);
        return true;}
        return false;
    }
}
