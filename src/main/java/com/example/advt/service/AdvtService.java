package com.example.advt.service;

import com.example.advt.dao.AdvtViewDAO;
import com.example.advt.domain.Advt;
import com.example.advt.domain.MessageUser;
import com.example.advt.repos.AdvtRepository;
import com.example.advt.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *06.11.2019
 */
@Service
public class AdvtService {
    @Autowired
    private AdvtRepository advtRepository;
    @Autowired
    private MessageRepository messageRepository;

    public List<AdvtViewDAO> advtAndMessage(Long us){
        List<AdvtViewDAO> viewMessage = new ArrayList<>();
        List<Advt> adverList = advtRepository.findByUserId(us);
        List<MessageUser> messageUserList = messageRepository.findByIdToUser(us);
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
            adV.setArticle(ad.getArticle());
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
        return viewMessage;
    }
    public int advtCount(){
        return advtRepository.findAll().size();
    }
    public int messagCount(){
        return messageRepository.findAll().size();
    }
}
