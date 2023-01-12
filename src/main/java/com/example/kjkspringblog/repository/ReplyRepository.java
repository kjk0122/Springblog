package com.example.kjkspringblog.repository;


import com.example.kjkspringblog.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Override
    void deleteById(Long aLong);

    List<Reply> findAllByOrderByCreatedAtDesc();
}
