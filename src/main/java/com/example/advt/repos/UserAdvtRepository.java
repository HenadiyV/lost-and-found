package com.example.advt.repos;

import com.example.advt.domain.Advt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAdvtRepository  extends JpaRepository<Advt,Long> {

    List<Advt> findByUserId(Long id);
}
