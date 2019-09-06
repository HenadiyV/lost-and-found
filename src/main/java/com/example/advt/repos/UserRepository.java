package com.example.advt.repos;


import com.example.advt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
//    User findByUsername(String name);
    User findByEmail(String email);

    User findById(int id);

//    User findByGoogleUserName(String username);
//
//    User findByGoogleName(String username);
}