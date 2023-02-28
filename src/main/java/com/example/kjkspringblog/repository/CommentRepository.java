package com.example.kjkspringblog.repository;


import com.example.kjkspringblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    void deleteById(Long aLong);

    List<Comment> findAllByOrderByCreatedAtDesc();
}
