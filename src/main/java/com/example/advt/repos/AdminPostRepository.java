package com.example.advt.repos;

import com.example.advt.domain.AdminPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AdminPostRepository extends JpaRepository<AdminPost, Long> {
    List<AdminPost> findAllById(Long id);
    Optional<AdminPost> findById(Long id);
    List<AdminPost> findByActive(boolean act);
}
