package com.example.kjkspringblog.dto;

import com.example.kjkspringblog.entity.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.title = reply.getTitle();
        this.content = reply.getContent();
        this.username = reply.getUsername();
        this.createdAt = reply.getCreatedAt();
        this.modifiedAt = reply.getModifiedAt();

    }

}