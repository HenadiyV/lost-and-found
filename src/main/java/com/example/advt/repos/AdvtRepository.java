package com.example.advt.repos;

import com.example.advt.domain.Advt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdvtRepository extends JpaRepository<Advt,Long> {
    Page<Advt> findByCategory_Id(int category, Pageable pageable);
    Page<Advt> findByCategory_IdAndSubcategory_Id(int category, int id, Pageable pageable);
   // Page<Advt> findAll(Pageable pageable);
    Page<Advt> findByCategory_IdAndFound(int category,boolean found,Pageable pageable);
    Page<Advt> findByUserId(Long id,Pageable pageable);
    List<Advt> findAll();
    List<Advt> findByUserId(Long id);

}
