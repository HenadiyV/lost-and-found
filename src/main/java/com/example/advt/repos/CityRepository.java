package com.example.advt.repos;

import com.example.advt.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City,Integer> {
}
