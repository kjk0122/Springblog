package com.example.kjkspringblog.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String username;
    private String content;
}