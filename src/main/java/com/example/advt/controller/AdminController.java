package com.example.advt.controller;

import com.example.advt.dao.AdvtViewDAO;
import com.example.advt.dao.CityDAO;
import com.example.advt.dao.UserDAO;
import com.example.advt.domain.*;
import com.example.advt.dto.CategoryDto;
import com.example.advt.dto.CityDto;
import com.example.advt.dto.UserRolyDTO;
import com.example.advt.exceptions.NoEntityException;
import com.example.advt.repos.*;
import com.example.advt.service.AdvtService;
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
import java.util.stream.Collectors;

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
    @Autowired
    private AdvtService advtService;

    @GetMapping("admin")
    public String adminPage(Map<String, Object> model) {
        if (userService.userAdmin()) {
            model.put("adm", 1);

        }
        int user_count = userService.userCount();
        model.put("user_count", user_count);
        int advt_count = advtService.advtCount();
        model.put("advt_count", advt_count);
        int message_count = advtService.messagCount();
        model.put("mes_count", message_count);
        model.put("admin_start", "admin");

        return "admin-page";
    }

    @RequestMapping("city1")
    public String getCityView(Principal principal) {

        if (principal != null && userService.userAdmin() || userService.baseAdmin(principal)) {
            return "city";
        }
        return "admin-page";
    }

    @GetMapping("citys")
    public String getCityViewNew(Principal principal, Map<String, Object> model) {

        if (principal != null && userService.userAdmin() || userService.baseAdmin(principal)) {
            CityDto cityList = new CityDto((List<City>) cityRepository.findAll());
            List<City> cityListNew = cityList.getListCitys();
            model.put("cityList", cityListNew);
            model.put("city", "city");
            model.put("adm", 1);
            return "admin-page";
        }
        return "index";
    }

    @RequestMapping("category")
    public String getCategoryView(Principal principal) {

        if (principal != null && userService.userAdmin() || userService.baseAdmin(principal)) {
            return "category";
        }
        return "admin-page";
    }

    @RequestMapping("subcategory")
    public String getCategoryView(Principal principal, Map<String, Object> model) {

        if (principal != null && userService.userAdmin() || userService.baseAdmin(principal)) {
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
        if (principal != null && userService.userAdmin() || userService.baseAdmin(principal)) {
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

        if (principal != null && userService.userAdmin() || userService.baseAdmin(principal)) {
            List<Advt> advtList = advtRepository.findAll();

            model.put("advtList", advtList);
            model.put("adm", 1);
            return "admin-page";
        }
        return "index";
    }

    @GetMapping("message")
    public String messageAdmin(Principal principal, Map<String, Object> model) {
        if (principal != null && userService.userAdmin() || userService.baseAdmin(principal)) {
            AdminPost ap = new AdminPost();
            List<AdminPost> adminPostList = adminPostRepository.findAll();
            Collections.sort(adminPostList);

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
            AdminPost adminPost = adminPostRepository.findById(id).get();

            adminPostRepository.delete(adminPost);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "message/hidden", method = RequestMethod.POST)
    public @ResponseBody
    boolean hiddenAdminPost(@RequestBody Long id) {
        if (id != 0) {
            AdminPost adminPost = adminPostRepository.findById(id).get();
            if (adminPost != null) {
                adminPost.setActive(false);
                adminPostRepository.save(adminPost);
            }
            return true;
        }
        return false;
    }


    Long us = 0L;

    @GetMapping("user-advt1")
    public String usId(@RequestParam Long idUser) {
        us = idUser;
        return "redirect:/admin/user-advt";
    }

    @GetMapping("user-advt")
    public String userAdvtList(Principal principal, Map<String, Object> model,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {

        if (us > 0) {


            String user = userRepository.findById(us).get().getName();
            List<AdvtViewDAO> viewMessage = advtService.advtAndMessage(us);


            if (userService.userAdmin() || userService.baseAdmin(principal)) {
                model.put("adm", 1);
            }

            Page<AdvtViewDAO> pviewMessage = userService.searchUserPage(viewMessage, pageable);
            if (viewMessage.size() == 0) {
                model.put("listEmpty", true);
            }
            model.put("url", "/admin/user-advt");
            model.put("viewMessage", pviewMessage);
            model.put("us", 2);
            model.put("user", user);
            model.put("usr", 0);

            return "page-user";
        }
        return "index";
    }

    @GetMapping("user")
    public String userList(Principal principal, Map<String, Object> model) {
        if (principal != null && userService.userAdmin() || userService.baseAdmin(principal)) {
            List<User> userListTemp = userService.userList();
            if (userListTemp.size() > 0) {
                model.put("userList", userListTemp);
                model.put("ban", true);
                model.put("adm", 1);
                return "admin-page";
            } else {
                model.put("user", "Not user");
            }
        }
        return "index";
    }

    @RequestMapping(value = "user/delete", method = RequestMethod.POST)
    public @ResponseBody
    boolean deleteUser(@RequestBody Long id) {
        if (id != 0) {
            Optional<User> user = userRepository.findById(id);

            User us = user.get();
            if (us != null) {
                us.getRoles().clear();
                List<Advt> advtList = advtRepository.findByUserId(id);
                List<MessageUser> messageUsersList = messageRepository.findAll();
                for (Advt advt : advtList) {
                    for (MessageUser messageUser : messageUsersList) {
                        if (messageUser.getIdAdvt() == advt.getId()) {
                            messageRepository.delete(messageUser);
                        }
                    }
                    advtRepository.delete(advt);
                }
                userRepository.delete(us);
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
                List<User> userList = userService.userList();
                return userList;
            }
        }
        return null;
    }

    @RequestMapping(value = "user/test", method = RequestMethod.GET)
    public @ResponseBody
    List<User> testUser(Map<String, Object> model) {
        List<User> userList = userService.userList();
        model.put("ban", true);
        return userList;

    }

    @GetMapping(value = "user-detalis")
    public String detalisUser(Map<String, Object> model) {
        List<User> userList = userRepository.findAll();
        List<UserDAO> userDAOList = new ArrayList<>();
        for (User us : userList) {
            List<Advt> advtList = advtRepository.findByUserId(us.getId());
            UserDAO userDAO = new UserDAO(us.getId(), us.getName(), us.getPhone(), us.getEmail(), us.isActive(), us.getNameSocial(), advtList.size());
            userDAOList.add(userDAO);

        }

        model.put("userList", userDAOList);

        model.put("adm", 1);
        model.put("detalis", true);
        return "admin-page";

    }

    @GetMapping(value = "user-role")
    public String roleUser(Map<String, Object> model) {
        List<User> userList = userRepository.findAll();
        List<UserRolyDTO> userRolList = new ArrayList<>();
        boolean adm = false;
        boolean usR = false;
        boolean mod = false;
        for (User us : userList) {
            for (Role rl : us.getRoles()) {
                if (rl.name().equals(("ADMIN"))) {
                    adm = true;
                }
                if (rl.name().equals(("USER"))) {
                    usR = true;
                }
                if (rl.name().equals(("MODERATOR"))) {
                    mod = true;
                }
            }
            UserRolyDTO usRl = new UserRolyDTO(us.getId(), us.getEmail(), us.getName(), us.isActive(), usR, adm, mod);
            userRolList.add(usRl);

            adm = false;
            usR = false;
            mod = false;
        }
        model.put("userList", userRolList);

        model.put("adm", 1);
        model.put("role", true);

        model.put("roles", Role.values());
        return "admin-page";

    }

    @PostMapping(value = "user-role")
    public String userSave(
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);

        return "redirect:/admin/user-role";
    }
}
