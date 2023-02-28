package com.example.kjkspringblog.entity;

import com.example.kjkspringblog.dto.CommentRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@RequiredArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long bid;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String content;


    public Comment(CommentRequestDto commentRequestDto, Board board, User user) {
        this.bid=board.getId();
        this.username=user.getUsername();
        this.content = commentRequestDto.getContent();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }
}
