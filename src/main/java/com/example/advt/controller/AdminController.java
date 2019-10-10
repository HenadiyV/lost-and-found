package com.example.advt.controller;

import com.example.advt.domain.*;
import com.example.advt.exceptions.NoEntityException;
import com.example.advt.repos.*;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *29.09.2019
 */
@Controller
//@PreAuthorize("hasAthority('ADMIN')")
@RequestMapping("admin")
public class AdminController {
@Autowired
private CategoryRepository categoryRepository;
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private AdvtRepository advtRepository;
    @Autowired
    private AdminPostRepository adminPostRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

@GetMapping
public String adminPage(Map<String, Object> model){
    if(userService.userAdmin()){
        model.put("adm", 1);
    }
    return "admin-page";
}
    @RequestMapping("city")
    public String getCityView(Principal principal) {

 if(principal!=null&&userService.userAdmin()){
   return "city" ;
 }
                return "admin_page";


    }
    @RequestMapping("subcategory")
    public String getCategoryView(Principal principal,Map<String, Object> model) {

        if(principal!=null&&userService.userAdmin()){
            List<Category> categoryList=categoryRepository.findAll();
            List<Subcategory> list=subcategoryRepository.findAll();
            model.put("subcategory",list);
            model.put("category",categoryList);

                model.put("adm", 1);


            return "admin-page" ;
        }
        return "/";


    }
    @RequestMapping("advt")
    public String getAdvtView(Principal principal,Map<String, Object> model) {

        if(principal!=null&&userService.userAdmin()){
            List<Advt> advtList=advtRepository.findAll();

            model.put("advtList",advtList);
            model.put("adm", 1);
            return "admin-page" ;
        }
        return "/";


    }
    @GetMapping("message")
    public String messageAdmin(Principal principal,Map<String, Object> model){
        if(principal!=null&&userService.userAdmin()){
            Iterable<AdminPost> adminPostList=adminPostRepository.findAll();
           // List<Advt> advtList=advtRepository.findAll();

            model.put("postList",adminPostList);
            model.put("adm", 1);
            return"message-to-admin";
        }
        return "/";

    }
    @RequestMapping(value="message/delete",method=RequestMethod.POST)
    public @ResponseBody
    boolean deleteAdminPost(@RequestBody Long id) throws NoEntityException {
        if(id!=0){
            AdminPost adminPost=adminPostRepository.findById(id).orElseThrow(()->new NoEntityException(id));
            return true;
        }
        return false;
    }
    @RequestMapping(value="message/hidden",method=RequestMethod.POST)
    public @ResponseBody
    boolean hiddenAdminPost(@RequestBody int id){
        if(id!=0){
//            Subcategory oldSubcategory=subcategoryRepository.findById(id);
//            oldSubcategory.setName("Not name");
//            subcategoryRepository.save(oldSubcategory);
            // subcategoryRepository.delete(oldSubcategory);
            return true;
        }
        return false;
    }

    @GetMapping("user")
    public String userList(Principal principal,Map<String, Object> model){
        if(principal!=null&&userService.userAdmin()){
          //  Iterable<AdminPost> adminPostList=adminPostRepository.findAll();
             List<User> userList=userRepository.findAll();
if(userList.size()>0){
            model.put("userList",userList);
            model.put("adm", 1);
            return"admin-page";
}else{
    model.put("user","Not user");
}
        }
        return "index";
    }
    @RequestMapping(value="user/delete",method=RequestMethod.POST)
    public @ResponseBody
    boolean deleteUser(@RequestBody Long id)  {
        if(id!=0){
            Optional<User> user=userRepository.findById(id);
            User us=user.get();
            if(us!=null){
                userRepository.delete(us);
            }
            return true;
        }
        return false;
    }
    @RequestMapping(value="user/lock",method=RequestMethod.POST)
    public @ResponseBody
    boolean lockUser(@RequestBody Long id)  {
        if(id!=0){
            Optional<User> user=userRepository.findById(id);
            User us=user.get();
            if(us!=null){

                us.setActive(false);
                userRepository.save(us);
            }
            return us.isActive();
        }
        return true;
    }
    @RequestMapping(value="user/unlock",method=RequestMethod.POST)
    public @ResponseBody
    List<User> unlockUser(@RequestBody Long id)  {
        if(id!=0){
            Optional<User> user=userRepository.findById(id);
            User us=user.get();
            if(us!=null){

                us.setActive(true);
                userRepository.save(us);


            List<User> userList=userRepository.findAll();


            return userList;
            }
        }
        return null;
    }
    @RequestMapping(value="user/test",method=RequestMethod.GET)
    public @ResponseBody
    List<User> testUser()  {
                List<User> userList=userRepository.findAll();
                return userList;

        }
    @RequestMapping(value="user/detalis",method=RequestMethod.GET)
    public @ResponseBody
    List<User> detalisUser()  {
        List<User> userList=userRepository.findAll();
        return userList;

    }
    }
//@GetMapping("category")
//    public List<Category> getListCategory(){
//    return categoryRepository.findAll();
//}
//    @GetMapping("subcategory")
//    public List<Subcategory> getListSubCategory(Map<String, Object> model){
//     SubCategoryDAO subCategoryDTO= new SubCategoryDAO((List<Subcategory>)subcategoryRepository.findAll());
//        return subCategoryDTO.getSubcategoryList();
//
//    }
//    @GetMapping("subcategory/{id}")
//    public Subcategory getOneSubCategory(@PathVariable("id") Subcategory subcategory ){
//            return subcategory;
//    }
//    @PostMapping("subcategory")
//    public Subcategory createSubcategory(@RequestBody Subcategory subcategory){
//    return subcategoryRepository.save(subcategory);
//    }
//    @PutMapping("subcategory/{id}")
//    public Subcategory updateSubcategory(@RequestBody Subcategory newSubcategory, @PathVariable int id){
//
//       Subcategory subcategory= subcategoryRepository.findById(id);
//        subcategory.setCategory(newSubcategory.getCategory());
//        subcategory.setName( newSubcategory.getName());
//        return subcategoryRepository.save(subcategory);
//
//    }
//}
