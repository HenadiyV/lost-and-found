package com.example.advt.controller;

import com.example.advt.dao.AdvtEditDAO;
import com.example.advt.dao.AdvtViewDAO;
import com.example.advt.dao.GuestMessageViewDAO;
import com.example.advt.domain.*;
import com.example.advt.repos.*;
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

    @GetMapping
    public String mainPage(Principal principal, Map<String, Object> model,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            List<Advt> adverList = advtRepository.findByUserId(user.getId());
            List<MessageUser> messageUserList = messageRepository.findByIdToUser(user.getId());

            List<AdvtViewDAO> viewMessage = new ArrayList<>();

            for (Advt ad : adverList) {
                AdvtViewDAO adV = new AdvtViewDAO();

                int coun = 0;
                Date d = null;
                for (MessageUser mes : messageUserList) {
                    if (mes.getIdAdvt() == ad.getId() && mes.isActive()) {
                        coun++;
//                   if(d==null){
//
//                   }
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

            if(userService.userAdmin()){
                model.put("adm", 1);
            }

            Page<AdvtViewDAO> pviewMessage = searchPatientPage(viewMessage, pageable);
            model.put("url", "main");
            model.put("viewMessage", pviewMessage);
            model.put("us", 2);
            // model.put("num", pviewMessage.getTotalElements());, int size
            return "page-user";
        } else {
            return "redirect:/";
        }
    }

    public Page<AdvtViewDAO> searchPatientPage(List<AdvtViewDAO> list, Pageable pageable) {
        List<AdvtViewDAO> patientsList = new ArrayList<AdvtViewDAO>();
        list.sort(Comparator.comparing(AdvtViewDAO::getAdvtId).reversed());
        patientsList.addAll(list);
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > patientsList.size() ? patientsList.size() : (start + pageable.getPageSize());

        return new PageImpl<AdvtViewDAO>(patientsList.subList(start, end), pageable, patientsList.size());
    }

    @GetMapping("/edit")
    public String editAdvt(AdvtEditDAO advtEdit, @RequestParam(value = "advtId") Advt advt, Map<String, Object> model) {
        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);
        int Id = advt.getCategory().id;
        if (Id > 0) {
            Iterable<Subcategory> categoryList = subcategoryRepository.findAllById(Id);
            model.put("categoryList", categoryList);
        }
        List<String> statusList = Arrays.asList("Розшукується", "Зник", "Загубленно", "Знайденно");
        model.put("statusList", statusList);
        model.put("advt", advt);
        if(userService.userAdmin()){
            model.put("adm", 1);
        }

        return "page-edit-advt";
    }

    @PostMapping("/edit")
    public String editAdvtPost(Advt advt,
                               @RequestParam(required = false, value = "dataStart") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStart,
                               @RequestParam(required = false, value = "file")
                                       MultipartFile file,
                               Model model) throws IOException {
        boolean result = true;
        Advt advtOld = advtRepository.getOne(advt.getId());
        if (dataStart != advtOld.getDat()) {
            advt.setDat(dataStart);
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
        return "redirect:/main";
    }

    @GetMapping("message{idAdvt}")
    public String MessageList(@PathVariable(value = "idAdvt") Long Id, Map<String, Object> model) {

        model.put("idAdvt", Id);
        model.put("all",0);
        if(userService.userAdmin()){
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
        return "redirect:/main/message_active" + IdAdvt;
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
    public String messageAll(@PathVariable(value = "idAdvt") Long Id, Map<String, Object> model) {
        List<MessageUser> listMessage = messageRepository.findByIdAdvt(Id);
       Advt advt=advtRepository.getOne(Id);
       List<GuestMessageViewDAO>  guestMessageViewDAOList=new ArrayList<>();
       for(MessageUser messageUser: listMessage){
           GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),messageUser.getText(),messageUser.getContact(),messageUser.isActive(),messageUser.getDat(),
           advt.getId(),advt.getText(),advt.getCity().name,advt.getStatus(),advt.getSubcategory().name,advt.getDat());
           guestMessageViewDAOList.add(guestMessageViewDAO);
       }
        model.put("listMessage", guestMessageViewDAOList);
        model.put("all",0);
        if(userService.userAdmin()){
            model.put("adm", 1);
        }

        return "page-user-message";
    }

    @GetMapping("message_pasive{idAdvt}")
    public String messagePasive(@PathVariable(value = "idAdvt") Long Id, Map<String, Object> model) {
        List<MessageUser> listMessage = messageRepository.findByIdAdvtAndActive(Id, false);
        Advt advt=advtRepository.getOne(Id);
        List<GuestMessageViewDAO>  guestMessageViewDAOList=new ArrayList<>();
        for(MessageUser messageUser: listMessage){
            GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),messageUser.getText(),messageUser.getContact(),false,messageUser.getDat(),
                    advt.getId(),advt.getText(),advt.getCity().name,advt.getStatus(),advt.getSubcategory().name,advt.getDat());
            guestMessageViewDAOList.add(guestMessageViewDAO);
        }
        model.put("listMessage", guestMessageViewDAOList);
        model.put("all",0);
        if(userService.userAdmin()){
            model.put("adm", 1);
        }

        return "page-user-message";
    }

    @GetMapping("message_active{idAdvt}")
    public String messageActive(@PathVariable(value = "idAdvt") Long Id, Map<String, Object> model) {
        List<MessageUser> listMessage = messageRepository.findByIdAdvtAndActive(Id, true);
        Advt advt=advtRepository.getOne(Id);
        List<GuestMessageViewDAO>  guestMessageViewDAOList=new ArrayList<>();
        for(MessageUser messageUser: listMessage){
            GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),messageUser.getText(),messageUser.getContact(),true,messageUser.getDat(),
                    advt.getId(),advt.getText(),advt.getCity().name,advt.getStatus(),advt.getSubcategory().name,advt.getDat());
            guestMessageViewDAOList.add(guestMessageViewDAO);
        }
        model.put("listMessage", guestMessageViewDAOList);
        model.put("all",0);
        if(userService.userAdmin()){
            model.put("adm", 1);
        }

        return "page-user-message";
    }
    @GetMapping("all_message_active")
    public String allMessageActive(@AuthenticationPrincipal User user, Map<String, Object> model) {

//        Long id=Long.parseLong(Id);
        if(user!=null){
       List<MessageUser> listMessage = messageRepository.findByIdToUserAndActive(user.getId(),true);
            List<GuestMessageViewDAO>  guestMessageViewDAOList=new ArrayList<>();
            for(MessageUser messageUser: listMessage){
Advt advt=advtRepository.getOne(messageUser.getIdAdvt());
                GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),messageUser.getText(),messageUser.getContact(),messageUser.isActive(),messageUser.getDat(),
                        advt.getId(),advt.getText(),advt.getCity().name,advt.getStatus(),advt.getSubcategory().name,advt.getDat());
                guestMessageViewDAOList.add(guestMessageViewDAO);
            }
       model.put("listMessage", guestMessageViewDAOList);
       model.put("all",1);
        }
        if(userService.userAdmin()){
            model.put("adm", 1);
        }

        return "page-user-message";
    }

    @GetMapping("all_message")
    public String allMessage(@AuthenticationPrincipal User user, Map<String, Object> model) {

//        Long id=Long.parseLong(Id);
        if(user!=null){
            List<MessageUser> listMessage = messageRepository.findByIdToUser(user.getId());
            List<GuestMessageViewDAO>  guestMessageViewDAOList=new ArrayList<>();
            for(MessageUser messageUser: listMessage){
                Advt advt=advtRepository.getOne(messageUser.getIdAdvt());
                GuestMessageViewDAO guestMessageViewDAO = new GuestMessageViewDAO(messageUser.getId(),messageUser.getText(),messageUser.getContact(),messageUser.isActive(),messageUser.getDat(),
                        advt.getId(),advt.getText(),advt.getCity().name,advt.getStatus(),advt.getSubcategory().name,advt.getDat());
                guestMessageViewDAOList.add(guestMessageViewDAO);
            }
            model.put("listMessage", guestMessageViewDAOList);
            model.put("all",1);
        }
        if(userService.userAdmin()){
            model.put("adm", 1);
        }

        return "page-user-message";
    }
}
//        Set<AdvtViewDAO> list=searchPatient(patient);
// int page, int size
// new PageRequest(page, size)
// Collections.sort(list,Collections.reverseOrder());

//   photo=advt.getPhoto().toString();
//        if(file!=null){
//            photo=file.getName();
//        }
//        if(advt.getPhoto().toString()=="") {}else  {
//&& !file.getName().isEmpty()
//            photo = "noimage.png";
//        }

//  @RequestParam(required = false,value="messageAll",defaultValue = "0")String messageAll,

//    @RequestMapping(value = "/message1", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})

//        List <MessageUser> listMessage=messageRepository.findByIdAdvtAndActive(IdAdvt,true);
////        model.put("listMessage",listMessage);

//        List <MessageUser> listMessage=messageRepository.findByIdAdvtAndActive(IdAdvt,true);
//        model.put("listMessage",listMessage);