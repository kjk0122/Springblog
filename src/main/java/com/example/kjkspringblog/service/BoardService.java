package com.example.kjkspringblog.service;

import com.example.kjkspringblog.dto.BoardRequestDto;
import com.example.kjkspringblog.dto.BoardResponseDto;
import com.example.kjkspringblog.entity.Board;
import com.example.kjkspringblog.entity.User;
import com.example.kjkspringblog.entity.UserRoleEnum;
import com.example.kjkspringblog.jwt.JwtUtil;
import com.example.kjkspringblog.repository.BoardRepository;
import com.example.kjkspringblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, HttpServletRequest request) {
        System.out.println("입력받음");
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Board board =new Board(boardRequestDto, user);
            boardRepository.save(board);
            return new BoardResponseDto(board);
        } else {
            return null;
        }
    }


    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoards() {

        List<BoardResponseDto> list = new ArrayList<>();

        List<Board> boardList;
        boardList = boardRepository.findAllByOrderByCreatedAtDesc();

        for (Board board : boardList) {
            list.add(new BoardResponseDto(board));
        }

        return list;
    }

    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지않음")
        );
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        // 토큰에서 사용자 정보 가져오기
        Claims claims = jwtUtil.getUserInfoFromToken(token);

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user =  userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            if(user.getRole()== UserRoleEnum.ADMIN) {
                Board board = boardRepository.findById(id).orElseThrow(
                        () -> new NullPointerException("해당 데이터는 존재하지 않습니다.")
                );
                board.update(requestDto);
                return new BoardResponseDto(board);
            }
            else {
            return null;
        }
    }

    @Transactional
    public String deleteBoard(Long id, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);

        // 토큰이 있는 경우에만 삭제 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            boardRepository.deleteById(id);

            return "삭제가 완료 되었습니다";
        } else {
            return null;
        }
    }

}