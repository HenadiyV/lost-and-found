package com.example.advt.dao;

import com.example.advt.domain.Category;
import com.example.advt.domain.City;
import com.example.advt.domain.MessageUser;
import com.example.advt.domain.Subcategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *23.09.2019
 */
@Data
public class AdvtViewDAO {
    public String category;
   public String subcategory;
    public String city;
    public Date dataS;
   public String photo;
   public String textAdvt;
    public boolean activAdvt;

    public String status;
    public String characters;
    public Long userId;
    public Long AdvtId;
  public int countMess;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
  public Date datMess;
}
