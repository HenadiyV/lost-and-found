package com.example.advt.dao;

import com.example.advt.domain.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *14.09.2019
 */
@Data
public class AdvtDTO {
    //  private String category;
     @NotNull(message="Виберіть місто")
    private City city;
    private int category;
    private int subcategory;
//    @NotNull(message="Виберіть категорію")
//     private Animal animal;
//    @NotNull(message="Виберіть категорію")
//     private People people;
//    @NotNull(message="Виберіть категорію")
//     private Thing thing;

    @NotEmpty(message="Введіть текст")
    private String textAdvt;
    private String status;
   private String chracters;
    private String url;
    public AdvtDTO() {
    }


}
