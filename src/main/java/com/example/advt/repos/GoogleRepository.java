package com.example.advt.repos;

import com.example.advt.domain.GoogleUserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleRepository extends JpaRepository<GoogleUserAuth,String> {
}
