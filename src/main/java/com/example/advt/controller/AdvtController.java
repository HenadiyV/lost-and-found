package com.example.advt.controller;


import com.example.advt.dao.AdvtDAO;
import com.example.advt.domain.*;
import com.example.advt.repos.*;
import com.example.advt.service.AdvtService;
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
    //    @Value("${animals.path}")
//    private String animalsPath;
//    @Value("${upload.path}")
//    private String uploadPath;
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
    @Autowired
    private AdvtService advService;

    @GetMapping
    public String pageAdvt(Model model) {
        return "page-advt";
    }

    // добавить обьявление
    String stat = "";
    Category cat_temp;
    String ur = "";

    @GetMapping("/add-advt")
    public String pageAddAdvt(Principal principal, AdvtDAO advtDAO,
                              @RequestParam(required = false, value = "status") String status,
                              @RequestParam(required = false, value = "category") Category category,
                              @RequestParam(required = false, value = "url") String url, Map<String, Object> model) throws ParseException {
        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);
        model.put("advt", advtDAO);
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            if (!user.isActive()) {

                return "redirect:/blocked" + user.getId();
            }
        }
        try {
            Integer Id = category.getId();
            if (category.getId() > 0) {
                Iterable<Subcategory> categoryList = subcategoryRepository.findByCategoryId(Id);
                model.put("categoryList", categoryList);
            }
            model.put("category", Id);
            model.put("status", status);
            model.put("url", url);

            return "page-add-advt";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/add-advt")
    public String pageAddAdvtPost(Principal principal, @Valid AdvtDAO advtDAO, BindingResult bindingResult, Model model,
                                  @RequestParam(required = false, value = "dataStart") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStart,
                                  @RequestParam("subcategory") Integer subcategory, @RequestParam(required = false, value = "file")
                                          MultipartFile file) throws ParseException, IOException {
        boolean result = true;
        try{
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date newDate = dateFormat.parse(dateFormat.format(new Date()));
        boolean corektCity = false;
        if (advtDAO.getCity() != null) {
            corektCity = advService.corectCity(advtDAO.getCity().getName());
        }
        if (bindingResult.hasErrors() || !corektCity) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("advtDAO", advtDAO);
            if (advtDAO.getCity() == null) {
                model.addAttribute("cityEr", "Не вибранно місто");
            }
            if (corektCity == false) {
                model.addAttribute("cityEr", "Не вибранно місто");
            }
            if (advtDAO.getSubcategory() == 0) {
                model.addAttribute("categoryEr", "Не вибранно категорию.");
            }
            if (advtDAO.getStatus() == null) {
                model.addAttribute("statusEr", "Не вказано статус.");
            }

            if (advtDAO.getTextAdvt().isEmpty()) {
                model.addAttribute("textAdverEr", "Не вказано текст оголошеня.");
            }
            Iterable<City> cityList = cityRepository.findAll();
            model.addAttribute("cityList", cityList);

            if (advtDAO.getCategory() > 0) {
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

        Subcategory sub = new Subcategory();
        sub = subcategoryRepository.findById(subcategory).get();
        String photo = "";
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            photo = advService.fileName(file);
        } else photo = advService.nameImageFileSubcategory(sub.getName(), sub.getCategory().getId());
        User user = new User();
        if (principal != null) {
            user = (User) userService.loadUserByUsername(principal.getName());
        }
        if (dataStart != null) {
            if (newDate.after(dataStart)) {
                newDate = dataStart;
            }
        } else {
            dataStart = newDate;
        }
        if (result && user != null) {
            advtDAO.setChracters("standart");
            boolean found = false;
            if (advtDAO.getStatus().equals("Знайденно")) {
                found = true;
            }
            Category cat = categoryRepository.getOne(advtDAO.getCategory());
            Long userId = user.getId();
            String art = advService.articl(0L, cat.getName(), sub.getName(), userId, advtDAO.getCity().getId());
            Advt adv = new Advt(advtDAO.getTextAdvt(), true, photo, advtDAO.getStatus(), advtDAO.getChracters()
                    , user.getId(), dataStart, found, art, cat, advtDAO.getCity(), sub);
          advtRepository.save(adv);
            model.addAttribute("advt", null);
            stat = "";
            cat_temp = null;
            ur = "";
        }

        return "redirect:" + advtDAO.getUrl();
        }catch (Exception ex){
            return "redirect:/";
        }
    }

    // удалить обьявление
    @PostMapping("/delete")
    public String sdvtDelete(@RequestParam("advtDel") Long Id, @RequestParam("url") String url) throws IOException {
        Advt advt = advtRepository.getOne(Id);
        if (advt != null) {
            if (!advt.getPhoto().equals("noimage.png")) {
                userService.deleteMyFile(advt.getPhoto());
            }
            advtRepository.delete(advt);
        }

        return "redirect:" + url;
    }
}
