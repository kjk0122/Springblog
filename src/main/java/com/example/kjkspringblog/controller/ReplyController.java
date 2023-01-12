package com.example.kjkspringblog.controller;

import com.example.kjkspringblog.dto.BoardRequestDto;
import com.example.kjkspringblog.dto.BoardResponseDto;
import com.example.kjkspringblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class ReplyController {

    private final BoardService boardService;

    @PostMapping("/reply")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {
        return boardService.createBoard(boardRequestDto, request);
    }

    @GetMapping("/replys")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }
    @GetMapping("/{id}/reply") //단일조회
    public BoardResponseDto getBoard(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.getBoard(id);
        return boardResponseDto;
    }
    @PutMapping("/{id}/reply")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        BoardResponseDto boardResponseDto = boardService.updateBoard(id, requestDto, request);
        // 응답 보내기 (업데이트된 상품 id)
        return boardResponseDto;
    }
    @DeleteMapping("/{id}/reply")
    public String deleteBoard(@PathVariable Long id, HttpServletRequest request) {
        return boardService.deleteBoard(id, request);
    }
}
