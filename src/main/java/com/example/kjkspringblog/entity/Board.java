package com.example.kjkspringblog.entity;

import com.example.kjkspringblog.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private String title;
    @Column(nullable = false)
    private String content;


    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
    }
    public Board(BoardRequestDto requestDto, Long userId) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
    }
    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
