package com.example.advt.repos;


import com.example.advt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByName(String name);
    User findByUsername(String email);

  User findByActivationCode(String code);
   User findByEmail(String email);
User findByIdSocial(String idSocial);
 Optional<User> findById(Long id);

}