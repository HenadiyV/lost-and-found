package com.example.advt.controller;

import com.example.advt.dao.AdvtEditDAO;
import com.example.advt.dao.AdvtViewDAO;
import com.example.advt.dao.GuestMessageViewDAO;
import com.example.advt.domain.*;
import com.example.advt.repos.*;
import com.example.advt.service.AdvtService;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private boolean adm;
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
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvtService advtService;

    @GetMapping
    public String mainPage(Principal principal, Map<String, Object> model,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        if (principal != null) {

            User user = (User) userService.loadUserByUsername(principal.getName());
            List<AdvtViewDAO> viewMessage = advtService.advtAndMessage(user.getId());

            if (userService.userAdmin()||userService.baseAdmin(principal)) {
                model.put("adm", 1);
            }
            Page<AdvtViewDAO> pviewMessage = userService.searchUserPage(viewMessage, pageable);
            model.put("url", "/main");
            model.put("viewMessage", pviewMessage);
            model.put("us", 2);
            model.put("user", user.getName());
            return "page-user";
        } else {
            return "redirect:/";
        }
    }


    Advt adv;

    @GetMapping("/edit")
    public String editAdvt(Principal principal,AdvtEditDAO advtEdit,
                           @RequestParam(required = false, value = "advtId") Advt advt,
                           Map<String, Object> model) {
        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);
        try {

            if (adv == null && advt != null) {
                adv = advt;
            } else if (advt.getId() != adv.getId()) {
                adv = advt;
            } else {
                advt = adv;
            }
            int Id = advt.getCategory().id;
            if (Id > 0) {
                Iterable<Subcategory> categoryList = subcategoryRepository.findAllById(Id);
                model.put("categoryList", categoryList);
            }
            List<String> statusList = Arrays.asList("Розшукується", "Зник", "Загубленно", "Знайденно");
            model.put("statusList", statusList);
            model.put("advt", advt);
            if (userService.userAdmin()||userService.baseAdmin(principal)) {
                model.put("adm", 1);
            }

            return "page-edit-advt";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/edit")
    public String editAdvtPost(Advt advt,
                               @RequestParam(required = false, value = "dataStart") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStart,
                               @RequestParam(required = false, value = "file")
                                       MultipartFile file,
                               @RequestParam(required = false, value = "url") String url,
                               Model model) throws IOException {
        boolean result = true;
        Date day=new Date();
        Advt advtOld = advtRepository.getOne(advt.getId());
        if (dataStart != advtOld.getDat()) {
            day=new Date(dataStart.getTime()+86400000);
            advt.setDat(day);
        }
        if (!advt.getStatus().equals("Знайденно")) {
            advt.setFound(false);
        } else {
            advt.setFound(true);
        }
        String photo = "";

        String f = file.getOriginalFilename();
        if (!f.equals("")) {
            userService.deleteMyFile(advtOld.getPhoto());


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
        } else {

            photo = advt.getPhoto().toString();
        }

        advt.setPhoto(photo);

        advtRepository.save(advt);
        adv = null;
        return "redirect:"+url;
    }

    @GetMapping("message{idAdvt}")
    public String MessageList(Principal principal,@PathVariable(value = "idAdvt") Long Id, Map<String, Object> model) {

        model.put("idAdvt", Id);
        model.put("all", 0);
        model.put("usr",1 );
        if (userService.userAdmin()||userService.baseAdmin(principal)) {
            model.put("adm", 1);
        }
        return "page-user-message";
    }

    @GetMapping("/hide")
    public String hideMessage(@RequestParam(value = "idAdvt") Long IdAdvt,
                              @RequestParam(value = "idMess") Long Id,

                              Map<String, Object> model) {
        MessageUser mess = messageRepository.getOne(Id);
        mess.setActive(false);
        messageRepository.save(mess);
        return "redirect:/main/message_pasive" + IdAdvt;
    }

    @GetMapping("/unhide")
    public String unHideMessage(@RequestParam(value = "idAdvt") Long IdAdvt,
                                @RequestParam(value = "idMess") Long Id,

                                Map<String, Object> model) {
        MessageUser mess = messageRepository.getOne(Id);
        mess.setActive(true);
        messageRepository.save(mess);

        return "redirect:/main/message_active" + IdAdvt;
    }

    @GetMapping("/delete")
    public String deleteMessage(@RequestParam(value = "idAdvt") Long IdAdvt,
                                @RequestParam(value = "idMess") Long Id,
                                Map<String, Object> model) {
        MessageUser mess = messageRepository.getOne(Id);

        messageRepository.delete(mess);

        return "redirect:/main/message_all" + IdAdvt;
    }

    @GetMapping("message_all{idAdvt}")
    public String messageAll(Principal principal,@PathVariable(value = "idAdvt") Long Id, Map<String, Object> model) {
        List<MessageUser> listMessage = messageRepository.findByIdAdvt(Id);
        Advt advt = advtRepository.getOne(Id);
        int activ = 0;
        int pasiv = 0;
        List<GuestMessageViewDAO> guestMessageViewDAOList = new ArrayList<>();

        for (MessageUser messageUser : listMessage) {

            if (messageUser.isActive()) {
                activ++;
            } else {
                pasiv++;
            }
            GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),
                    messageUser.getText(), messageUser.getContact(), messageUser.isActive(), messageUser.getDat(),
                    advt.getId(), advt.getText(), advt.getCity().name, advt.getStatus(), advt.getSubcategory().name,
                    advt.getDat());
            guestMessageViewDAOList.add(guestMessageViewDAO);
        }
        model.put("listMessage", guestMessageViewDAOList);
        model.put("all", 0);
        model.put("activ", activ);
        model.put("pasiv", pasiv);
        model.put("usr",1 );
        if (userService.userAdmin()||userService.baseAdmin(principal)) {
            model.put("adm", 1);
        }

        return "page-user-message";
    }

    @GetMapping("message_pasive{idAdvt}")
    public String messagePasive(Principal principal,@PathVariable(value = "idAdvt") Long Id, Map<String, Object> model) {
        List<MessageUser> listMessage = messageRepository.findByIdAdvtAndActive(Id, false);
        Advt advt = advtRepository.getOne(Id);
        int activ = 0;
        int pasiv = 0;
        List<MessageUser> listMessageAll = messageRepository.findByIdAdvt(Id);
        for (MessageUser mess : listMessageAll) {
            if (mess.isActive()) {
                activ++;
            } else {
                pasiv++;
            }
        }
        model.put("activ", activ);
        model.put("pasiv", pasiv);

        List<GuestMessageViewDAO> guestMessageViewDAOList = new ArrayList<>();
        for (MessageUser messageUser : listMessage) {
            if (messageUser.isActive()) {
                activ++;
            } else {
                pasiv++;
            }
            GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),
                    messageUser.getText(), messageUser.getContact(), false, messageUser.getDat(),
                    advt.getId(), advt.getText(), advt.getCity().name, advt.getStatus(), advt.getSubcategory().name,
                    advt.getDat());
            guestMessageViewDAOList.add(guestMessageViewDAO);
        }
        model.put("listMessage", guestMessageViewDAOList);
        model.put("all", 0);
        model.put("usr",1 );

        if (userService.userAdmin()||userService.baseAdmin(principal)) {
            model.put("adm", 1);
        }

        return "page-user-message";
    }

    @GetMapping("message_active{idAdvt}")
    public String messageActive(Principal principal,@PathVariable(value = "idAdvt") Long Id, Map<String, Object> model) {

        List<MessageUser> listMessage = messageRepository.findByIdAdvtAndActive(Id, true);
        Advt advt = advtRepository.getOne(Id);
        int activ = 0;
        int pasiv = 0;
        List<MessageUser> listMessageAll = messageRepository.findByIdAdvt(Id);
        for (MessageUser mess : listMessageAll) {
            if (mess.isActive()) {
                activ++;
            } else {
                pasiv++;
            }
        }
        model.put("activ", activ);
        model.put("pasiv", pasiv);
        List<GuestMessageViewDAO> guestMessageViewDAOList = new ArrayList<>();

        for (MessageUser messageUser : listMessage) {

            GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),
                    messageUser.getText(), messageUser.getContact(), true, messageUser.getDat(),
                    advt.getId(), advt.getText(), advt.getCity().name, advt.getStatus(),
                    advt.getSubcategory().name, advt.getDat());

            guestMessageViewDAOList.add(guestMessageViewDAO);
        }
        model.put("listMessage", guestMessageViewDAOList);
        model.put("all", 0);
        model.put("usr",1 );
        if (userService.userAdmin()||userService.baseAdmin(principal)) {
            model.put("adm", 1);
        }

        return "page-user-message";
    }

    @GetMapping("all_message_active")
    public String allMessageActive(Principal principal, Map<String, Object> model) {

        if (principal != null) {
            User user=userRepository.findByName(principal.getName());
            List<MessageUser> listMessage = messageRepository.findByIdToUserAndActive(user.getId(), true);
            List<GuestMessageViewDAO> guestMessageViewDAOList = new ArrayList<>();

            for (MessageUser messageUser : listMessage) {

                Advt advt = advtRepository.getOne(messageUser.getIdAdvt());
                GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),
                        messageUser.getText(), messageUser.getContact(), messageUser.isActive(), messageUser.getDat(),
                        advt.getId(), advt.getText(), advt.getCity().name, advt.getStatus(), advt.getSubcategory().name,
                        advt.getDat());

                guestMessageViewDAOList.add(guestMessageViewDAO);
            }
            model.put("listMessage", guestMessageViewDAOList);
            model.put("all", 1);
            model.put("usr",1 );
        }
        if (userService.userAdmin()||userService.baseAdmin(principal)) {
            model.put("adm", 1);
        }

        return "page-user-message";
    }

    @GetMapping("all_message")
    public String allMessage(Principal principal, Map<String, Object> model) {

        if (principal != null) {
            User user=userRepository.findByName(principal.getName());
            List<MessageUser> listMessage = messageRepository.findByIdToUser(user.getId());
            List<GuestMessageViewDAO> guestMessageViewDAOList = new ArrayList<>();

            for (MessageUser messageUser : listMessage) {

                Advt advt = advtRepository.getOne(messageUser.getIdAdvt());
                GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),
                        messageUser.getText(), messageUser.getContact(), messageUser.isActive(), messageUser.getDat(),
                        advt.getId(), advt.getText(), advt.getCity().name, advt.getStatus(), advt.getSubcategory().name,
                        advt.getDat());

                guestMessageViewDAOList.add(guestMessageViewDAO);
            }
            model.put("listMessage", guestMessageViewDAOList);
            model.put("all", 1);
            model.put("usr",1 );
        }
        if (userService.userAdmin()||userService.baseAdmin(principal)) {
            model.put("adm", 1);
        }
        return "page-user-message";
    }
}
