package com.example.advt.dto;

import com.example.advt.dao.UserAdvtDAO;
import com.example.advt.domain.Advt;

import java.util.ArrayList;
import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *19.10.2019
 */
public class UserAdvtDTO {
    private UserAdvtDAO userAdvtDAO;
    private Advt adv;
    List<Advt> advtList=new ArrayList<>();
    List<UserAdvtDAO> advtDaoList=new ArrayList<>();
    public UserAdvtDTO(List<Advt> advtList) {
       if(advtList.size()>0){
          for(Advt adv:advtList){
              UserAdvtDAO usDao =new UserAdvtDAO(adv.getUserId(),adv.getId(),adv.getText(),adv.isActive()
                      ,adv.getPhoto(),adv.getStatus(),adv.getArticle(),adv.getCharacters(),adv.getDat(),adv.getCity().getName(),adv.getSubcategory().getName());
              advtDaoList.add(usDao);
          }
       }

    }

    public UserAdvtDTO(Advt adv) {
       this.userAdvtDAO=new UserAdvtDAO(adv.getUserId(),adv.getId(),adv.getText(),adv.isActive()
                ,adv.getPhoto(),adv.getStatus(),adv.getArticle(),adv.getCharacters(),adv.getDat(),adv.getCity().getName(),adv.getSubcategory().getName());

    }

    public UserAdvtDAO getUserAdvtDAO() {
        return userAdvtDAO;
    }

    public List<UserAdvtDAO> getAdvtDaoList() {
        return advtDaoList;
    }
}
