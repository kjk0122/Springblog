package com.example.kjkspringblog.repository;

import com.example.kjkspringblog.entity.ApiUseTime;
import com.example.kjkspringblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUseTimeRepository extends JpaRepository<ApiUseTime, Long> {
    Optional<ApiUseTime> findByUser(User user);
}