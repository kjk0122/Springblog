package com.example.kjkspringblog.controller;

import com.example.kjkspringblog.dto.BoardRequestDto;
import com.example.kjkspringblog.dto.BoardResponseDto;
import com.example.kjkspringblog.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "글쓰기")
    @PostMapping("")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {
        return boardService.createBoard(boardRequestDto, request);
    }

    @ApiOperation(value = "전체 목록 가져오기")
    @GetMapping("/lists")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }
    @ApiOperation(value = "단일 글 가져오기")
    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.getBoard(id);
        return boardResponseDto;
    }
    @ApiOperation(value = "글 수정하기")
    @PutMapping("/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        BoardResponseDto boardResponseDto = boardService.updateBoard(id, requestDto, request);
        // 응답 보내기 (업데이트된 상품 id)
        return boardResponseDto;
    }
    @ApiOperation(value = "글 삭제")
    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id, HttpServletRequest request) {
        return boardService.deleteBoard(id, request);
    }
}
