package com.example.kjkspringblog.controller;

import com.example.kjkspringblog.dto.BoardRequestDto;
import com.example.kjkspringblog.dto.BoardResponseDto;
import com.example.kjkspringblog.dto.CommentRequestDto;
import com.example.kjkspringblog.dto.CommentResponseDto;
import com.example.kjkspringblog.service.BoardService;
import com.example.kjkspringblog.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 쓰기")
    @PostMapping("/{boardId}")
    public CommentResponseDto createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.createComment(boardId, commentRequestDto, request);
    }
    @ApiOperation(value = "댓글 수정")
    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        CommentResponseDto commentResponseDto = commentService.updateComment(id, requestDto, request);
        // 응답 보내기 (업데이트된 상품 id)
        return commentResponseDto;
    }
    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id, HttpServletRequest request) {
        return commentService.deleteComment(id, request);
    }
}
