package com.example.advt.dao;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *07.10.2019
 */
@Data
public class AdminPostDAO {
    @NotEmpty(message="Введіть ім'я")
    private String name;
    @NotEmpty(message="Введіть email")
    private String email;
    @NotEmpty(message="Введіть текст")
    private String messageUser;
    @NotEmpty(message="Виберіть тему")
    private String topic;
}
