package com.example.advt.controller;


import com.example.advt.dao.AdvtDAO;
import com.example.advt.domain.*;
import com.example.advt.repos.*;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
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
     return "page-advt";
 }
// добавить обьявление
    String stat="";
 Category cat_temp;
 String ur="";
    @GetMapping("/add-advt")
    public String pageAddAdvt(AdvtDAO advtDAO,
                              @RequestParam(required = false, value ="status") String status,
                              @RequestParam(required = false, value ="category") Category category ,
                              @RequestParam(required = false, value ="url") String url,Map<String, Object> model)throws ParseException{
        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);
    try {
        if(stat.isEmpty()&& !status.isEmpty()){
            stat=status;
        }else{status=stat;}
        if(cat_temp==null && category!=null){
            cat_temp=category;
        }else{category=cat_temp;}
        if(ur.isEmpty()&& !url.isEmpty()){
            ur=url;
        }else{url=ur;}
        Integer Id=category.getId();
       if(category.getId()>0){
          Iterable<Subcategory> categoryList = subcategoryRepository.findByCategoryId(Id);
            model.put("categoryList", categoryList);
        }
       model.put("category", Id);
       model.put("status", status);
        model.put("url", url);

        return "page-add-advt";
    }catch (Exception e){
    return "redirect:/";
}
    }
    @PostMapping("/add-advt")
    public String pageAddAdvtPost(Principal principal, @Valid AdvtDAO advtDAO, BindingResult bindingResult, Model model,
              @RequestParam(required = false, value = "dataStart") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStart,
              @RequestParam("subcategory") Integer subcategory , @RequestParam(required = false, value = "file")
                MultipartFile file)throws ParseException, IOException{
        boolean result = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = dateFormat.parse(dateFormat.format(new Date()));
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            if (advtDAO.getCity() == null) {
                model.addAttribute("cityEr", "Не вибранно місто");
            }
            if (advtDAO.getSubcategory() == 0) {
                model.addAttribute("categoryEr", "Не вибранно категорию.");
            }
            if (advtDAO.getStatus() == null) {
                model.addAttribute("statusEr", "Не вказано статус.");
            }
            if (dataStart == null) {
                model.addAttribute("dataSotpEr", "Не вказано дату .");
            }else{
                if(startDate.after(dataStart)){
                    model.addAttribute("dataSotpEr", "Не коректну дату .");
                }
            }
            if (advtDAO.getTextAdvt().isEmpty()) {
                model.addAttribute("textAdverEr", "Не вказано текст оголошеня.");
            }
            Iterable<City> cityList = cityRepository.findAll();
            model.addAttribute("cityList", cityList);

            if(advtDAO.getCategory()>0){
                Iterable<Subcategory> categoryList = subcategoryRepository.findByCategoryId(advtDAO.getCategory());
                model.addAttribute("categoryList", categoryList);
            advtDAO.setCategory(advtDAO.getCategory());
            }
            advtDAO.setChracters("standart");
            model.addAttribute("category", advtDAO.getCategory());
            model.addAttribute("status", advtDAO.getStatus());
            model.addAttribute("url", advtDAO.getUrl());
            return "page-add-advt";
        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        Date startDate = dateFormat.parse(dateFormat.format(new Date()));
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
if (!user.isActive()){

            return"redirect:/blocked"+user.getId();
}
        if(startDate.after(dataStart)){
            startDate=dataStart;
        }
            if (result  && user !=null) {
                advtDAO.setChracters("standart");
boolean found=false;
if(advtDAO.getStatus().equals("Знайденно")){
    found=true;
}
            Category cat=categoryRepository.getOne(advtDAO.getCategory());
            Subcategory sub= new Subcategory();
                sub = subcategoryRepository.findById(subcategory).get();
            Long userId=user.getId();
            String art=articl(cat.getName(),sub.getName(), userId,advtDAO.getCity().getId());


             Advt adv = new Advt(advtDAO.getTextAdvt(),true, photo, advtDAO.getStatus(), advtDAO.getChracters(),user.getId(), startDate,found, art, cat , advtDAO.getCity(),sub);
            advtRepository.save(adv);
                stat="";
                cat_temp=null;
                ur="";
        }

        return "redirect:"+ advtDAO.getUrl();
    }
public String articl(String cat,String sub, Long idUser,int idCity){
        List<Advt> advts=advtRepository.findAll();
        Long id=0L;
        for(Advt ad:advts){
            if(ad.getId()>id){
                id=ad.getId();
            }
        }
        String str=String.valueOf(id+1)+"-"+cat.substring(0,1)+"-"+sub.substring(0,1)+"-"+String.valueOf(idUser)+"-"+String.valueOf(idCity);

        return str;

}
    // удалить обьявление
    @PostMapping("/delete")
    public String sdvtDelete(@RequestParam("advtDel") Long Id,@RequestParam("url") String url) throws IOException {
     Advt advt= advtRepository.getOne(Id);
    if(advt!=null)
    {
        if(!advt.getPhoto().equals("noimage.png")){
      userService.deleteMyFile(advt.getPhoto());
        }
    advtRepository.delete(advt);
    }


        return "redirect:"+url;
    }
}
