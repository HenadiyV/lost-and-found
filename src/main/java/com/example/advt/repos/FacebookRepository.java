package com.example.advt.repos;

import com.example.advt.domain.FacebookUserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacebookRepository extends JpaRepository<FacebookUserAuth,String> {
}
