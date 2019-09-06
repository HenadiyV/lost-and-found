package com.example.advt.repos;

import com.example.advt.domain.Advt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvtRepository extends JpaRepository<Advt,Integer> {
}
