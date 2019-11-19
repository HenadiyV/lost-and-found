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

    public String articl(Long advtId,String cat,String sub, Long idUser,int idCity){
       //Advt ad=advtRepository.findByIdMatches();
      Long id=0L;
        if(advtId==0){
        List<Advt> advts=advtRepository.findAll();

        for(Advt ad:advts){
            if(ad.getId()>id){
                id=ad.getId();
            }
        }
            id++;
        }else id=advtId;
        String str=String.valueOf(id)+"-"+cat.substring(0,1)+"-"+sub.substring(0,1)+"-"+String.valueOf(idUser)+"-"+String.valueOf(idCity);

        return str;

    }

    public String nameImageFileSubcategory(String subcategory,int category){
        String fileName="";

        if(category==1){
            if(subcategory.equals("Дитина")){
                fileName="Baby.png";
            }
            if(subcategory.equals("Жінка")){
                fileName="Woman.png";
            }
            if(subcategory.equals("Чоловік")){
                fileName="Men.png";
            }
            if(subcategory.equals("Группа")){
                fileName="Group.png";
            }


        }
        if(category==2){
        if(subcategory.equals("Кіт")){
            fileName="Cat.png";
        }
        if(subcategory.equals("Собака")){
            fileName="Dog.png";
        }
        if(subcategory.equals("Попугай")){
            fileName="Parrot.png";
        }
        if(subcategory.equals("Гризун")){
            fileName="Raf.png";
        }
        if(subcategory.equals("Тхір")){
            fileName="Ferret.png";
        }

    }
    if(category==3){
        if(subcategory.equals("Документи")){
            fileName="Document.png";
        }
        if(subcategory.equals("Телефон")){
            fileName="Phone.png";
        }
        if(subcategory.equals("Гаманець")){
            fileName="Purse.png";
        }
        if(subcategory.equals("Ключі")){
            fileName="Key.png";
        }
        if(subcategory.equals("Парасолька")){
            fileName="Umbrella.png";
        }
        if(subcategory.equals("Шапка")){
            fileName="Hat.png";
        }
        if(subcategory.equals("Рукавички")){
            fileName="Gloves.png";
        }
    }
    if(subcategory.equals("Інше")){
            fileName="noimage.png";
        }
        if(fileName.isEmpty()){
            fileName="noimage.png";
        }
        return fileName;
    }
}
