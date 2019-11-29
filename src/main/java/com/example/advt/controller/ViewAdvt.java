package com.example.advt.controller;

import com.example.advt.dao.AdvtViewDAO;
import com.example.advt.dao.CityDAO;
import com.example.advt.dao.GuestMessageDAO;
import com.example.advt.dao.SubCategoryDAO;
import com.example.advt.domain.*;
import com.example.advt.dto.CityDto;
import com.example.advt.dto.SearchDto;
import com.example.advt.repos.AdvtRepository;
import com.example.advt.repos.CityRepository;
import com.example.advt.repos.MessageRepository;
import com.example.advt.repos.SubcategoryRepository;
import com.example.advt.service.AdvtService;
import com.example.advt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AdvtService advtService;
    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "city", method = RequestMethod.GET)
    public @ResponseBody
    List<CityDAO> getCityCityCity2(@RequestParam(value = "filter") String referal) {
        CityDto cityList = new CityDto((List<City>) cityRepository.findAll());
        List<City> cityListNew = cityList.getListCitys();
        List<CityDAO> cityDAOList = new ArrayList<>();
        String tex = referal.toUpperCase();
        for (City cit : cityListNew) {
            if (cit.getName().toUpperCase().contains(tex)) {

                CityDAO cityDAO = new CityDAO();
                cityDAO.setIdCity(cit.getId());
                cityDAO.setNameCity(cit.getName());
                cityDAOList.add(cityDAO);
            }
        }
        return cityDAOList;
    }

    @GetMapping("people-lost")
    public String ViewLostPeople(Principal principal, Map<String, Object> model,
                                 @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = userService.roleUser(principal);
        model.put("category", 1);
        model.put("us", rl);
        model.put("url", "people-lost");
        model.put("status", "Розшукується");

        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(1, false, pageable);
        for (Advt adv : adverList) {
            if (adv.getArticle() == null) {
                String art = advtService.articl(adv.getId(), adv.getCategory().getName(), adv.getSubcategory().getName(), adv.getUserId(), adv.getCity().getId());
                adv.setArticle(art);
                advtRepository.save(adv);
            }
        }
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());
        model.put("categorySearch", 1);
        model.put("found", 0);
        model.put("urlSearch", "page-people");
        List<Subcategory> listSubcategory = subcategoryRepository.findByCategory_Id(1);
        model.put("subcategoryList", listSubcategory);
        return "page-people";
    }

    @GetMapping("people-found")
    public String ViewFoundPeople(Principal principal, Map<String, Object> model,
                                  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = userService.roleUser(principal);
        model.put("category", 1);
        model.put("us", rl);
        model.put("url", "people-found");
        model.put("status", "Розшукується");

        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(1, true, pageable);
        for (Advt adv : adverList) {
            if (adv.getArticle() == null) {
                String art = advtService.articl(adv.getId(), adv.getCategory().getName(), adv.getSubcategory().getName(), adv.getUserId(), adv.getCity().getId());
                adv.setArticle(art);
                advtRepository.save(adv);
            }
        }
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());
        model.put("categorySearch", 1);
        model.put("found", 1);
        model.put("urlSearch", "page-people");
        List<Subcategory> listSubcategory = subcategoryRepository.findByCategory_Id(1);
        model.put("subcategoryList", listSubcategory);
        return "page-people";
    }

    @GetMapping("animal-lost")
    public String ViewLostAnimal(Principal principal, Map<String, Object> model,
                                 @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = userService.roleUser(principal);
        model.put("url", "animal-lost");
        model.put("category", 2);
        model.put("us", rl);
        model.put("status", "Зник");

        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(2, false, pageable);
        for (Advt adv : adverList) {
            if (adv.getArticle() == null) {
                String art = advtService.articl(adv.getId(), adv.getCategory().getName(), adv.getSubcategory().getName(), adv.getUserId(), adv.getCity().getId());
                adv.setArticle(art);
                advtRepository.save(adv);
            }
        }
        model.put("listAdver", adverList);
        model.put("categorySearch", 2);
        model.put("found", 0);
        model.put("urlSearch", "page-animal");
        List<Subcategory> listSubcategory = subcategoryRepository.findByCategory_Id(2);
        model.put("subcategoryList", listSubcategory);
        return "page-animal";
    }

    @GetMapping("animal-found")
    public String ViewFoundAnimal(Principal principal, Map<String, Object> model,
                                  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = userService.roleUser(principal);
        model.put("url", "animal-found");
        model.put("category", 2);
        model.put("categorySearch", 2);
        model.put("us", rl);
        model.put("status", "Зник");
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(2, true, pageable);
        for (Advt adv : adverList) {
            if (adv.getArticle() == null) {
                String art = advtService.articl(adv.getId(), adv.getCategory().getName(), adv.getSubcategory().getName(), adv.getUserId(), adv.getCity().getId());
                adv.setArticle(art);
                advtRepository.save(adv);
            }
        }
        model.put("listAdver", adverList);
        model.put("found", 1);
        model.put("urlSearch", "page-animal");
        List<Subcategory> listSubcategory = subcategoryRepository.findByCategory_Id(2);
        model.put("subcategoryList", listSubcategory);
        return "page-animal";
    }

    @GetMapping("thing-lost")
    public String ViewLostThing(Principal principal, Map<String, Object> model,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = userService.roleUser(principal);
        List<Subcategory> listSubcategory = subcategoryRepository.findByCategory_Id(3);
        model.put("subcategoryList", listSubcategory);
        model.put("url", "thing-lost");
        model.put("category", 3);
        model.put("us", rl);
        model.put("status", "Загубленно");
        model.put("categorySearch", 3);
        model.put("found", 0);
        model.put("urlSearch", "page-thing");
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(3, false, pageable);
        for (Advt adv : adverList) {
            if (adv.getArticle() == null) {
                String art = advtService.articl(adv.getId(), adv.getCategory().getName(), adv.getSubcategory().getName(), adv.getUserId(), adv.getCity().getId());
                adv.setArticle(art);
                advtRepository.save(adv);
            }
        }
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());
        return "page-thing";
    }

    @GetMapping("thing-found")
    public String ViewFoundThing(Principal principal, Map<String, Object> model,
                                 @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        int rl = userService.roleUser(principal);
        List<Subcategory> listSubcategory = subcategoryRepository.findByCategory_Id(3);
        model.put("subcategoryList", listSubcategory);
        model.put("url", "thing-found");
        model.put("category", 3);
        model.put("us", rl);
        model.put("status", "Загубленно");

        model.put("categorySearch", 3);
        model.put("found", 1);
        model.put("urlSearch", "page-thing");
        Page<Advt> adverList = advtRepository.findByCategory_IdAndFound(3, true, pageable);
        for (Advt adv : adverList) {
            if (adv.getArticle() == null) {
                String art = advtService.articl(adv.getId(), adv.getCategory().getName(), adv.getSubcategory().getName(), adv.getUserId(), adv.getCity().getId());
                adv.setArticle(art);
                advtRepository.save(adv);
            }
        }
        model.put("listAdver", adverList);
        model.put("num", adverList.getTotalElements());
        return "page-thing";
    }

    SearchDto search_temp;
    int found1;
    int cat;

    @GetMapping("search")
    public String thingSearch(Principal principal, SearchDto search,
                              @RequestParam(required = false, defaultValue = "-1", value = "found") int found,
                              @RequestParam(required = false, defaultValue = "-1", value = "categorySearch") int category,
                              @RequestParam(required = false, defaultValue = "search", value = "urlSearch") String url,
                              Map<String, Object> model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        try {
if(search.getCity() != null||search.getSubcategory() != null){
            int rl = userService.roleUser(principal);
            Page<Advt> adverList = null;

            boolean tem = false;
            boolean sear = false;
            boolean stat = false;
            if (search_temp != null) {
                tem = true;
            }
            if (search.getCity() != null) {
                sear = true;
            }
            if (search.getSubcategory() != null) {
                sear = true;
            }
            if (tem != false && sear != false) {

                if (!search_temp.equals(search)) {
                    search_temp = search;
                }
            }
            if (sear == false && tem != false) {
                search = search_temp;
            }
            if (tem == false && sear != false) {
                search_temp = search;
            }
            if (search.getFound() > 0) {
                stat = true;
            }
            int cit = 0;
            if (search.getCity() != null) {
                cit = search.getCity().getId();
            }
            if (search.getSubcategory() != null && cit > 0) {
                adverList = advtRepository.findByCategory_IdAndSubcategory_IdAndCity_IdAndFound(search.getCategorySearch(), search.getSubcategory().getId(), cit, stat, pageable);
            }
            if (search.getSubcategory() == null && cit > 0) {
                adverList = advtRepository.findByCategory_IdAndCity_IdAndFound(search.getCategorySearch(), cit, stat, pageable);
            }
            if (search.getSubcategory() != null && cit == 0) {
                adverList = advtRepository.findByCategory_IdAndSubcategory_IdAndFound(search.getCategorySearch(), search.getSubcategory().getId(), stat, pageable);
            }
            List<Subcategory> listSubcategory = subcategoryRepository.findByCategory_Id(search.getCategorySearch());
            model.put("subcategoryList", listSubcategory);

            model.put("url", search.getUrlSearch());
            model.put("category", search.getCategorySearch());
            model.put("us", rl);
            String statusTemp = "";
            switch (search.getCategorySearch()) {
                case 1:
                    statusTemp = "Розшукується";
                    break;
                case 2:
                    statusTemp = "Зник";
                    break;
                case 3:
                    statusTemp = "Загубленно";
                    break;
            }
            model.put("status", statusTemp);
            model.put("found", search.getFound());
            model.put("urlSearch", search.getUrlSearch());

            model.put("categorySearch", search.getCategorySearch());
            model.put("us", rl);
            assert adverList != null;
            if (adverList.getTotalElements() == 0) {
                model.put("searchMessage", true);
            }
            model.put("num", adverList.getTotalElements());
            model.put("listAdver", adverList);
            search.setCity(null);
            search.setSubcategory(null);
}
            return search.getUrlSearch();

        } catch (Exception e) {
            //return search.getUrlSearch();
           return "redirect:/";
        }
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

    @RequestMapping(value = "/add_message", method = RequestMethod.POST)
    public @ResponseBody
    boolean getSearchUserProfiles(@RequestBody GuestMessageDAO guestMessageDAO) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date createDate = dateFormat.parse(dateFormat.format(new Date().getTime()+86400000));
        int c = guestMessageDAO.getContact().length();
        int l = guestMessageDAO.getTextMessage().length();
        if (l < 101 && c < 30) {
            String contact = "";
            if (guestMessageDAO.getContact().isEmpty()) {
                contact = "Відсутній";
            } else {
                contact = guestMessageDAO.getContact();
            }
            MessageUser mess = new MessageUser(contact, guestMessageDAO.getTextMessage(), guestMessageDAO.getUserId(), guestMessageDAO.getIdAdver(), createDate, true);
            messageRepository.save(mess);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/advt", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> oneAdvt(@RequestParam(value = "idMess") Long id, Map<String, Object> model) {

        if (id > 0) {
            Advt advt = advtRepository.getOne(id);
            model.put("advt", advt);
            return model;
        }
        return null;
    }
}

