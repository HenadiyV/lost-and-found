package com.example.advt.controller;

import com.example.advt.dao.AdvtEditDAO;
import com.example.advt.domain.Advt;
import com.example.advt.domain.City;
import com.example.advt.domain.Subcategory;
import com.example.advt.domain.User;
import com.example.advt.repos.AdvtRepository;
import com.example.advt.repos.CategoryRepository;
import com.example.advt.repos.CityRepository;
import com.example.advt.repos.SubcategoryRepository;
import com.example.advt.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.*;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *18.09.2019
 */
@Controller
@RequestMapping("/main")
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private UserService userService;
    @Autowired
    private AdvtRepository advtRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
@GetMapping
    public String mainPage(Principal principal,Map<String, Object> model,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
    if(principal!=null){
        User user = (User) userService.loadUserByUsername(principal.getName());
        Page<Advt> adverList = advtRepository.findByUserId(user.getId(),  pageable);
        model.put("listAdver", adverList);
        model.put("us",2);
    return "page-user";
    }
    else{
        return "redirect:/";
    }
}


//@RequestMapping(value = "/edit/{advtId}", method = RequestMethod.GET)@PathParam(value = "advtId1") Long id,
    @GetMapping("/edit")
    public String editAdvt(AdvtEditDAO advtEdit ,@RequestParam(value="advtId") Advt advt, Map<String, Object> model){
        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);
//      Advt advt=advtRepository.getOne(id);
        Integer Id=advt.getCategory().id;
        if(Id>0) {
            Iterable<Subcategory> categoryList = subcategoryRepository.findByCategoryId(Id);
            model.put("categoryList", categoryList);
        }
//        List<Map<String, String>> statusList = new ArrayList<Map<String, String>>(){{
//        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "Розшукується"); }});
//            add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Зник"); }});
//            add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Загубленно"); }});
//            add(new HashMap<String, String>() {{ put("id", "4"); put("text", "Знайденно"); }});
//        }};
        List<String> statusList= Arrays.asList("Розшукується","Зник","Загубленно","Знайденно");
        model.put("statusList",statusList);
//        model.put("status",advt.getStatus());
//        model.put("cityName",advt.getCity().name);
//        model.put("cityId",advt.getCity().id);
//        model.put("subCategoryName",advt.getSubcategory().name);
//        model.put("subCategoryId",advt.getSubcategory().id);
//        model.put("dataS",advt.getDat());
//        model.put("photo",advt.getPhoto());
//        model.put("text",advt.getText());
        model.put("advt",advt);
        return "page-edit-advt";
    }
    @PostMapping("/edit")
    public String editAdvtPost( Advt advt,
                               @RequestParam(required = false, value = "dataStart") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStart,
//   @ModelAttribute Advt advt,@RequestParam(value="advtId")                            @RequestParam(value="cityId")int cityId,
                            //  @RequestParam(value="subCategoryId")int subCategoryId,
//                               @RequestParam(value="dataS")@DateTimeFormat(pattern = "yyyy-MM-dd") Date dataS,
//                               @RequestParam(value="photo")String photo,
                               @RequestParam(required = false, value = "file")
                                           MultipartFile file,
                               Model model) throws IOException {
        boolean result = true;
        Advt advtOld=advtRepository.getOne(advt.getId());
        if(dataStart!=advtOld.getDat()){
            advt.setDat(dataStart);
        }

        String photo = "";
        if(file!=null&& !file.getOriginalFilename().isEmpty()){
           // deleteMyFile(advtOld.getPhoto());
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
        advt.setPhoto(photo);
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
//            model.mergeAttributes(errorMap);
//            if (advt.getCity() == null) {
//                model.addAttribute("cityEr", "Не вибранно місто");
//
//            }
//            if (advt.getSubcategory().id == 0) {
//                model.addAttribute("categoryEr", "Не вибранно категорию.");
//
//            }
//            if (advt.getStatus() == null) {
//                model.addAttribute("statusEr", "Не вказано статус.");
//
//            }
//            if (dataStart == null) {
//                model.addAttribute("dataSotpEr", "Не вказано дату .");
//
//            }
//            if (advt.getText().isEmpty()) {
//                model.addAttribute("textAdverEr", "Не вказано текст оголошеня.");
//            }
//            Iterable<City> cityList = cityRepository.findAll();
//            model.addAttribute("cityList", cityList);
////            Category categ=categoryRepository.getOne(advt.getCategory().id);
//            if(advt.getCategory().getId()>0){
//                Iterable<Subcategory> categoryList = subcategoryRepository.findByCategoryId(advt.getCategory().getId());
//                model.addAttribute("categoryList", categoryList);
//                advt.setCategory(advt.getCategory());
//            }

//            return "page-edit-advt";
//        }
advtRepository.save(advt);
    return "redirect:/main";
    }
    public void deleteMyFile(String fileName) throws IOException {
        //FileUtils.touch(new File("src/test/resources/fileToDelete.txt"));
        String nam=uploadPath+"\\"+fileName;
        FileUtils.forceDelete(FileUtils.getFile(nam));
    }
}
