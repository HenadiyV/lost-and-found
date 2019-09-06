package com.example.advt.repos;

import com.example.advt.domain.Thing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingRepository extends JpaRepository<Thing,Integer> {
}
