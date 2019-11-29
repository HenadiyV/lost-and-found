package com.example.advt.controller;

import com.example.advt.domain.Advt;
import com.example.advt.dao.UserAdvtDAO;
import com.example.advt.dto.UserAdvtDTO;
import com.example.advt.repos.UserAdvtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *19.10.2019
 */
@RestController
@RequestMapping("user-advt")
public class UserAdvtController {
    private final UserAdvtRepository userAdvtRepository;

    @Autowired
    public UserAdvtController(UserAdvtRepository userAdvtRepository) {
        this.userAdvtRepository = userAdvtRepository;
    }

    @GetMapping
    //@JsonView(Views.IdName.class)
    public List<UserAdvtDAO> listSubcategory(@RequestParam(value = "userId") Long id) {
        UserAdvtDTO userAdvtDTOList = new UserAdvtDTO((List<Advt>) userAdvtRepository.findByUserId(id));
        List<UserAdvtDAO> advtDAOList = new ArrayList<>();

        return userAdvtDTOList.getAdvtDaoList();

    }

    @GetMapping("{id}")
// @JsonView(Views.FullMessage.class)
    public UserAdvtDAO getOne(@PathVariable("id") Advt idAdvt) {

        Advt adv = userAdvtRepository.getOne(idAdvt.getId());
        UserAdvtDTO userAdvtDto = new UserAdvtDTO(adv);
        return userAdvtDto.getUserAdvtDAO();
    }
}
