package com.example.advt.dto;

import com.example.advt.domain.Advt;
import com.example.advt.domain.MessageUser;
import com.example.advt.domain.User;
import lombok.Data;

import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *03.11.2019
 */
@Data
public class UserAdvtMessageDTO {
    private User user;
    private List<Advt> advtList;
    private List<MessageUser> messageUserList;

    public UserAdvtMessageDTO() {
    }

    public UserAdvtMessageDTO(User user, List<Advt> advtList, List<MessageUser> messageUserList) {
        this.user = user;
        this.advtList = advtList;
        this.messageUserList = messageUserList;
    }
}
