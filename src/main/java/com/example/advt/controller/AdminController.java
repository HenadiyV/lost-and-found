package com.example.advt.controller;

import com.example.advt.dao.AdvtViewDAO;
import com.example.advt.dao.CityDAO;
import com.example.advt.domain.*;
import com.example.advt.dto.CategoryDto;
import com.example.advt.dto.CityDto;
import com.example.advt.exceptions.NoEntityException;
import com.example.advt.repos.*;
import com.example.advt.service.UserService;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *29.09.2019
 */
@Controller
//@PreAuthorize("hasAthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private AdvtRepository advtRepository;
    @Autowired
    private AdminPostRepository adminPostRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public String adminPage(Map<String, Object> model) {
        if (userService.userAdmin()) {
            model.put("adm", 1);
        }
        return "admin-page";
    }

    @RequestMapping("city1")
    public String getCityView(Principal principal) {

        if (principal != null && userService.userAdmin()) {
            return "city";
        }
        return "admin-page";
    }

    @GetMapping("citys")
    public String getCityViewNew(Principal principal, Map<String, Object> model) {

        if (principal != null && userService.userAdmin()) {
            CityDto cityList = new CityDto((List<City>) cityRepository.findAll());
            List<City> cityListNew = cityList.getListCitys();
            model.put("cityList", cityListNew);
            model.put("city", "city");
            model.put("adm", 1);
            return "admin-page";
        }
        return "index";
    }

//    @RequestMapping(value = "city", method = RequestMethod.GET)
//    public @ResponseBody
//    List<CityDAO> getCityCityCity2(@RequestParam(value = "filter") String referal) {
//        CityDto cityList = new CityDto((List<City>) cityRepository.findAll());
//        List<City> cityListNew = cityList.getListCitys();
//        List<CityDAO> cityDAOList = new ArrayList<>();
//        String tex = referal.toUpperCase();
//        for (City cit : cityListNew) {
//            if (cit.getName().toUpperCase().contains(tex)) {
//
//                CityDAO cityDAO = new CityDAO();
//                cityDAO.setIdCity(cit.getId());
//                cityDAO.setNameCity(cit.getName());
//                cityDAOList.add(cityDAO);
//            }
//        }
//        return cityDAOList;
//    }

    @RequestMapping("category")
    public String getCategoryView(Principal principal) {

        if (principal != null && userService.userAdmin()) {
            return "category";
        }
        return "admin-page";
    }

    @RequestMapping("subcategory")
    public String getCategoryView(Principal principal, Map<String, Object> model) {

        if (principal != null && userService.userAdmin()) {
            List<Category> categoryList = categoryRepository.findAll();
            List<Subcategory> list = subcategoryRepository.findAll();
            model.put("subcategory", list);
            model.put("category", categoryList);

            model.put("adm", 1);


            return "admin-page";
        }
        return "index";


    }

    @RequestMapping("category_new")
    public String categoryTest(Principal principal, Map<String, Object> model) {
        if (principal != null && userService.userAdmin()) {
            List<Category> categoryList = categoryRepository.findAll();

            model.put("categoryList", categoryList);
            int max = 0;
            for (Category cat : categoryList) {
                int n = cat.getId();
                if (n > max) {
                    max = n;
                }
            }
            model.put("adm", 1);
            model.put("max", max);

            return "admin-page";
        }
        return "index";
    }

    @RequestMapping("advt")
    public String getAdvtView(Principal principal, Map<String, Object> model) {

        if (principal != null && userService.userAdmin()) {
            List<Advt> advtList = advtRepository.findAll();

            model.put("advtList", advtList);
            model.put("adm", 1);
            return "admin-page";
        }
        return "index";


    }

    @GetMapping("message")
    public String messageAdmin(Principal principal, Map<String, Object> model) {
        if (principal != null && userService.userAdmin()) {
            Iterable<AdminPost> adminPostList = adminPostRepository.findAll();
            // List<Advt> advtList=advtRepository.findAll();

            model.put("postList", adminPostList);
            model.put("adm", 1);
            return "message-to-admin";
        }
        return "index";

    }

    @RequestMapping(value = "message/delete", method = RequestMethod.POST)
    public @ResponseBody
    boolean deleteAdminPost(@RequestBody Long id) throws NoEntityException {
        if (id != 0) {
            AdminPost adminPost = adminPostRepository.findById(id).orElseThrow(() -> new NoEntityException(id));
            return true;
        }
        return false;
    }

    @RequestMapping(value = "message/hidden", method = RequestMethod.POST)
    public @ResponseBody
    boolean hiddenAdminPost(@RequestBody int id) {
        if (id != 0) {
//            Subcategory oldSubcategory=subcategoryRepository.findById(id);
//            oldSubcategory.setName("Not name");
//            subcategoryRepository.save(oldSubcategory);
            // subcategoryRepository.delete(oldSubcategory);
            return true;
        }
        return false;
    }

    @GetMapping("user")
    public String userList(Principal principal, Map<String, Object> model) {
        if (principal != null && userService.userAdmin()) {
            //  Iterable<AdminPost> adminPostList=adminPostRepository.findAll();
User admin=new User();
            List<User> userList = userRepository.findAll();
            List<User> userListTemp =new ArrayList<>();
            for(User us:userList){
                for(Role rl:us.getRoles()){
//                    String rol=rl.getRole().toString();
               if(rl.name()!=("ADMIN"))
               {
                   userListTemp.add(us);
               }else {admin=us;}
                }
            }
            if (userListTemp.size() > 0) {
                model.put("userList", userListTemp);
                model.put("ban", true);
                model.put("adm", 1);
                model.put("admin", admin);
                return "admin-page";
            } else {
                model.put("user", "Not user");
            }
        }
        return "index";
    }

    Long us = 0L;

    @GetMapping("user-advt1")
    public String usId(@RequestParam Long idUser) {
        us = idUser;
        return "redirect:/admin/user-advt";
    }

    @GetMapping("user-advt")
    public String userAdvtList(Map<String, Object> model,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        List<Advt> adverList = new ArrayList<>();


        adverList = advtRepository.findByUserId(us);
        List<MessageUser> messageUserList = messageRepository.findByIdToUser(us);

        List<AdvtViewDAO> viewMessage = new ArrayList<>();

        for (Advt ad : adverList) {
            AdvtViewDAO adV = new AdvtViewDAO();

            int coun = 0;
            Date d = null;
            for (MessageUser mes : messageUserList) {
                if (mes.getIdAdvt() == ad.getId() && mes.isActive()) {
                    coun++;

                    d = mes.getDat();
                }
            }

            adV.setCountMess(coun);
            adV.setDatMess(d);
            adV.setSubcategory(ad.getSubcategory().name);
            adV.setAdvtId(ad.getId());
            adV.setActivAdvt(ad.isActiv());

            adV.setCategory(ad.getCategory().getName());
            adV.setCharacters(ad.getCharacters());
            adV.setCity(ad.getCity().getName());
            adV.setDataS(ad.getDat());
            adV.setPhoto(ad.getPhoto());
            adV.setStatus(ad.getStatus());
            adV.setTextAdvt(ad.getText());
            adV.setUserId(ad.getUserId());
            viewMessage.add(adV);

        }

        if (userService.userAdmin()) {
            model.put("adm", 1);
        }

        Page<AdvtViewDAO> pviewMessage = userService.searchUserPage(viewMessage, pageable);
        model.put("url", "user-advt");
        model.put("viewMessage", pviewMessage);
        model.put("us", 2);

        return "page-user";

    }

    @RequestMapping(value = "user/delete", method = RequestMethod.POST)
    public @ResponseBody
    boolean deleteUser(@RequestBody Long id) {
        if (id != 0) {
            Optional<User> user = userRepository.findById(id);
            User us = user.get();
            if (us != null) {
             //   userRepository.delete(us);
            }
            return true;
        }
        return false;
    }

    @RequestMapping(value = "user/lock", method = RequestMethod.POST)
    public @ResponseBody
    boolean lockUser(@RequestBody Long id) {
        if (id != 0) {
            Optional<User> user = userRepository.findById(id);
            User us = user.get();
            if (us != null) {

                us.setActive(false);
                userRepository.save(us);
            }
            return us.isActive();
        }
        return true;
    }

    @RequestMapping(value = "user/unlock", method = RequestMethod.POST)
    public @ResponseBody
    List<User> unlockUser(@RequestBody Long id) {
        if (id != 0) {
            Optional<User> user = userRepository.findById(id);
            User us = user.get();
            if (us != null) {

                us.setActive(true);
                userRepository.save(us);


                List<User> userList = userRepository.findAll();


                return userList;
            }
        }
        return null;
    }

    @RequestMapping(value = "user/test", method = RequestMethod.GET)
    public @ResponseBody
    List<User> testUser(Map<String, Object> model) {
        List<User> userList = userRepository.findAll();
        model.put("ban", true);
        return userList;

    }

    @GetMapping(value = "user-detalis")
    public String detalisUser(Map<String, Object> model) {
        List<User> userList = userRepository.findAll();
        model.put("userList", userList);

        model.put("adm", 1);
        model.put("detalis", true);
        return "admin-page";

    }
}
