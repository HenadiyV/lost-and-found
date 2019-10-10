package com.example.advt.repos;

import com.example.advt.domain.MessageUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageUser,Long> {
   List<MessageUser> findByIdAdvt(Long advt);
  List<MessageUser> findByIdToUser(Long userId);
  List<MessageUser> findByIdAdvtAndActive(Long advtId,boolean active);
    List<MessageUser> findByIdToUserAndActive(Long userId,boolean active);
}
