package com.example.advt.controller;


import com.example.advt.dao.AdvtDTO;
import com.example.advt.domain.*;
import com.example.advt.repos.*;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *22.08.2019
 */
@Controller
@RequestMapping("/advt")
public class AdvtController {
    @Value("${animals.path}")
    private String animalsPath;
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private AdvtRepository advtRepository;
    @Autowired
    private CityRepository cityRepository;

@Autowired
private SubcategoryRepository subcategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
 @GetMapping
    public String pageAdvt(Model model){
//userService.authUser(model);
     return "page-advt";
 }
//  ,
    @GetMapping("/add-advt")
    public String pageAddAdvt(AdvtDTO advtDto,
                              @RequestParam(required = false, value ="status") String status,
                              @RequestParam(required = false, value ="category") Category category ,
                              @RequestParam( value ="url") String url,Map<String, Object> model)throws ParseException{
        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);
      // Category categ=categoryRepository.getOne(categoryId);
        Integer Id=category.getId();
       if(category.getId()>0){
          Iterable<Subcategory> categoryList = subcategoryRepository.findByCategoryId(Id);
            model.put("categoryList", categoryList);
//           advt.setThing(null);
//           advt.setAnimal(null);
        }
       // userService.authUserMap(model);
       model.put("category", category);
       model.put("status", status);
        model.put("url", url);
        //advt.setCategory(category);
      //  advt.setChracters("standart");
       // advt.setStatus(status);
//////        int categoryId=Integer.parseInt(category);
//////        Optional<Category> categor=categoryRepository.findById(categoryId);



        return "page-add-advt";
    }
    @PostMapping("/add-advt")
    public String pageAddAdvtPost(Principal principal,
            @Valid AdvtDTO advtDto, BindingResult bindingResult, Model model,
                                  @RequestParam(required = false, value = "dataStart")
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStart,
//                                 @RequestParam("category") Category category ,
                              //  @RequestParam("url") String url ,
                                  @RequestParam("subcategory") Integer subcategory ,
                                  @RequestParam(required = false, value = "file")
                                          MultipartFile file)throws ParseException, IOException{
    // userService.authUser(model);
        boolean result = true;
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            if (advtDto.getCity() == null) {
                model.addAttribute("cityEr", "Не вибранно місто");

            }
            if (advtDto.getSubcategory() == 0) {
                model.addAttribute("categoryEr", "Не вибранно категорию.");

            }
            if (advtDto.getStatus() == null) {
                model.addAttribute("statusEr", "Не вказано статус.");

            }
            if (dataStart == null) {
                model.addAttribute("dataSotpEr", "Не вказано дату .");

            }
            if (advtDto.getTextAdvt().isEmpty()) {
                model.addAttribute("textAdverEr", "Не вказано текст оголошеня.");
            }
            Iterable<City> cityList = cityRepository.findAll();
            model.addAttribute("cityList", cityList);
//            Category categ=categoryRepository.getOne(advtDto.getCategory().id);
            if(advtDto.getCategory()>0){
                Iterable<Subcategory> categoryList = subcategoryRepository.findByCategoryId(advtDto.getCategory());
                model.addAttribute("categoryList", categoryList);
            advtDto.setCategory(advtDto.getCategory());
            }
            advtDto.setChracters("standart");
            return "page-add-advt";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = dateFormat.parse(dateFormat.format(new Date()));
        String photo = "";
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String fileName = file.getOriginalFilename();
            int startIndex = fileName.replaceAll("\\\\", "/").lastIndexOf("/");
            fileName = fileName.substring(startIndex + 1);
            photo = uuid + fileName;
            file.transferTo(new File(uploadPath + "/" + photo));
        }else photo="noimage.png";
        User user =new User();
        if(principal != null){
             user = (User) userService.loadUserByUsername(principal.getName());

        }

            if (result&&user!=null) {
                advtDto.setChracters("standart");
boolean found=false;
if(advtDto.getStatus().equals("Знайденно")){
    found=true;
}
//              String email= userService.authUserEmail();

            Category cat=categoryRepository.getOne(advtDto.getCategory());
            Subcategory sub=subcategoryRepository.getOne(subcategory);
            Long userId=user.getId();
             Advt adv = new Advt(advtDto.getTextAdvt(),true, photo, advtDto.getStatus(),advtDto.getChracters(),user.getId(), startDate,found, cat ,advtDto.getCity(),sub);


            advtRepository.save(adv);
        }
//        SimpleDateFormat dateFormat1 = null;
//        dateFormat1 = new SimpleDateFormat("dd MMMM YYYY");
//        String dts = dateFormat1.format(startDate);
//
//        model.addAttribute("dataSart", dts);
//        model.addAttribute("user", user);
//        model.addAttribute("adver", advt);
//        model.addAttribute("photo", photo);
//        model.addAttribute("photoUp", uploadPath + photo);
        return "redirect:"+advtDto.getUrl();
    }
}
