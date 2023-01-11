package com.example.kjkspringblog.repository;


import com.example.kjkspringblog.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    @Override
    void deleteById(Long aLong);

    List<Board> findAllByOrderByCreatedAtDesc();
}
