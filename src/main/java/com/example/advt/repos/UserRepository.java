package com.example.advt.repos;


import com.example.advt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByName(String name);
    User findByUsername(String email);
  //User findByUsername(String email);
////    User findByUsername(String name);
   User findByEmail(String email);
User findByIdSocial(String idSocial);
// User findOne(Long id);

//    User findByGoogleUserName(String username);
//
//    User findByGoogleName(String username);
}