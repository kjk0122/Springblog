package com.example.kjkspringblog.dto;

import com.example.kjkspringblog.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto{
    private final Long id;
    private final String title;
    private final String content;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.username = board.getUsername();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();

    }
}