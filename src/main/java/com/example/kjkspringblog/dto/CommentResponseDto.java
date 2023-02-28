package com.example.kjkspringblog.dto;

import com.example.kjkspringblog.entity.Board;
import com.example.kjkspringblog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long boardId;
    private Long id;

    private String boardName;

    private String commentName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Board board, Comment comment) {
        this.boardId=board.getId();
        this.id = comment.getId();
        this.boardName=board.getUsername();
        this.commentName=comment.getUsername();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.commentName=comment.getUsername();
        this.content = comment.getContent();
        this.modifiedAt = comment.getModifiedAt();
    }
}