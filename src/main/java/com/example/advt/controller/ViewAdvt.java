package com.example.advt.controller;

import com.example.advt.dao.AdvtViewDAO;
import com.example.advt.dao.GuestMessageDAO;
import com.example.advt.domain.Advt;
import com.example.advt.domain.MessageUser;
import com.example.advt.domain.User;
import com.example.advt.repos.AdvtRepository;
import com.example.advt.repos.MessageRepository;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *16.09.2019
 */
@Controller
@RequestMapping("/view")
public class ViewAdvt {
    @Autowired
    private AdvtRepository advtRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageRepository messageRepository;
    @GetMapping("people-lost")
    public String ViewLostPeople(Principal principal, Map<String, Object> model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        int rl = 0;
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            boolean bl = userService.userAdmin();
            if (bl) {
                rl = 2;
            } else rl = 1;
        }
        model.put("category", 1);
        model.put("us", rl);
        model.put("url", "people-lost");
        model.put("status", "Розшукується");
        model.put("lost",true);
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(1, false, pageable);
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());


        return "page-people";
    }

    @GetMapping("people-found")
    public String ViewFoundPeople(Principal principal, Map<String, Object> model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        int rl = 0;
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            boolean bl = userService.userAdmin();
            if (bl) {
                rl = 2;
            } else rl = 1;
        }
        model.put("category", 1);
        model.put("us", rl);
        model.put("url", "people-found");
        model.put("status", "Розшукується");
      //  model.put("lost",false);
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(1, true, pageable);
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());

        return "page-people";
    }
    @GetMapping("animal-lost")
    public String ViewLostAnimal(Principal principal, Map<String, Object> model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = 0;
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            boolean bl = userService.userAdmin();
            if (bl) {
                rl = 2;
            } else rl = 1;
        }
        model.put("url", "animal-lost");
        model.put("category", 2);
        model.put("us", rl);
        model.put("status", "Зник") ;
//        model.put("lost",true);
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(2, false, pageable);
        model.put("listAdver", adverList);
    //    model.put("num", adverList.getTotalElements());


        return "page-animal";
    }
    @GetMapping("animal-found")
    public String ViewFoundAnimal(Principal principal, Map<String, Object> model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = 0;
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            boolean bl = userService.userAdmin();
            if (bl) {
                rl = 2;
            } else rl = 1;
        }
        model.put("url", "animal-found");
        model.put("category", 2);
        model.put("us", rl);
        model.put("status", "Зник") ;
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(2, true, pageable);
        model.put("listAdver", adverList);
      //  model.put("num", adverList.getTotalElements());


        return "page-animal";
    }
    @GetMapping("thing-lost")
    public String ViewLostThing(Principal principal, Map<String, Object> model,
                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = 0;
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            boolean bl = userService.userAdmin();
            if (bl) {
                rl = 2;
            } else rl = 1;
        }
        model.put("url", "thing-lost");
        model.put("category", 3);
        model.put("us", rl);
        model.put("status", "Загубленно");
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(3, false, pageable);
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());


        return "page-thing";
    }
    @GetMapping("thing-found")
    public String ViewFoundThing(Principal principal, Map<String, Object> model,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = 0;
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            boolean bl = userService.userAdmin();
            if (bl) {
                rl = 2;
            } else rl = 1;
        }
        model.put("url", "thing-found");
        model.put("category", 3);
        model.put("us", rl);
        model.put("status", "Загубленно");
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(3, true, pageable);
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());


        return "page-thing";
    }
    private static final int MESSAGE_LENGTH = 100;

    @RequestMapping(value = "/message1", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody
    String message1(@RequestParam(value = "textMessage") String textMessage) {
        int a = MESSAGE_LENGTH - textMessage.length();

        if (a > 0) {

            return String.valueOf(a) + " ";
        }
        return "";
    }

    @GetMapping("/message")

    public String UserMessage() {

        return "message";
    }

    @RequestMapping(value="/add_message",method=RequestMethod.POST)

    public  @ResponseBody void  getSearchUserProfiles(@RequestBody GuestMessageDAO guestMessageDAO) throws ParseException {
     SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date createDate = dateFormat.parse(dateFormat.format(new Date()));
       // guestMessageDAO.getIdAdver();
        int l= guestMessageDAO.getTextMessage().length();
        MessageUser mess=new MessageUser(guestMessageDAO.getContact(), guestMessageDAO.getTextMessage(), guestMessageDAO.getUserId(), guestMessageDAO.getIdAdver(),createDate,true);

        messageRepository.save(mess);
    }
    @RequestMapping(value="/advt",method=RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> oneAdvt(@RequestParam(value="idMess") Long id, Map<String, Object> model){
        //Page<Advt> advt=advtRepository.findAll(pageable);
        if(id>0){
        Advt advt=advtRepository.getOne(id);
        model.put("advt",advt);
        return model;
        }
        return null;
    }
}
// @RequestMapping(value="/edit",method=RequestMethod.POST)
//    public @ResponseBody
//    boolean editSubcategory(@RequestBody AdvtViewDAO advtViewDAO,
//                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
//        //Page<Advt> advt=advtRepository.findAll(pageable);
//        return true;
//    }
//    @GetMapping("/add_message")
//
//    public String UserAddMessage(GuestMessageDAO guestMessage,Map<String, Object> model) {
//model.put("guest",guestMessage);
//        return "add_message";
//    }

//    @RequestMapping(value="/add_message",method=RequestMethod.POST)
//    public void submitForm(@RequestBody GuestMessageDAO guestMessage)
//    {
//        //all data available with Wrapper classcontact,textMessage,userId,idAdver
//        MessageUser mess=new MessageUser();
//    }
    ////////////////////////////////////
   // @RequestMapping(value = "/add_message", method = RequestMethod.POST, produces = {"text/html; charset=UTF-8"} )

//   public @ResponseBody String addMessage(
//                        @RequestParam(value = "par1") Long idAdver,
//                        @RequestParam(value = "par2") Long userId,
//                        @RequestParam(value = "contact") String contact,
//                        @RequestParam(value = "textMessage") String textMessage,
//                        @RequestParam(value = "url") String url
//                           ) {
//
//MessageUser mess=new MessageUser(contact,textMessage,userId,idAdver);
//messageRepository.save(mess);
////            if(guestMessage!=null){}
//        //return "redirect:"+guestMessage.getUrl();
//       return "";
//    }

//    @PostMapping("/messageUser")
//    public String ReturnUserId(@RequestParam(required = false, value = "idUser") int id, @RequestParam("idAdver") int idAdver, Model model) {
//        if (id > 0)
//            model.addAttribute("userId", id);
//        else {
//            model.addAttribute("userId", 0);
//        }
//        model.addAttribute("idAdver", idAdver);
//        return "message";
//    }
    ///////////////////////////////

