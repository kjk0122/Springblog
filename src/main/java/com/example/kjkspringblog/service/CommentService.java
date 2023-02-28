package com.example.kjkspringblog.service;

import com.example.kjkspringblog.dto.CommentRequestDto;
import com.example.kjkspringblog.dto.CommentResponseDto;
import com.example.kjkspringblog.entity.Board;
import com.example.kjkspringblog.entity.Comment;
import com.example.kjkspringblog.entity.User;
import com.example.kjkspringblog.entity.UserRoleEnum;
import com.example.kjkspringblog.jwt.JwtUtil;
import com.example.kjkspringblog.repository.BoardRepository;
import com.example.kjkspringblog.repository.CommentRepository;
import com.example.kjkspringblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
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
            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("글이 존재하지 않습니다.")
            );
            Comment comment =new Comment(commentRequestDto, board, user);
            commentRepository.save(comment);
            return new CommentResponseDto(board,comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        // 토큰에서 사용자 정보 가져오기
        Claims claims = jwtUtil.getUserInfoFromToken(token);

        // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
        User user =  userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        // 운영자인가요? 확인하기
        if(user.getRole()== UserRoleEnum.ADMIN) {
            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("해당 데이터는 존재하지 않습니다.")
            );
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        }
        else { //아니면 나가!
            System.out.println("관리자 아님!");
            return null;
        }
    }

    @Transactional
    public String deleteComment(Long id, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        // 토큰에서 사용자 정보 가져오기
        Claims claims = jwtUtil.getUserInfoFromToken(token);

        // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
        User user =  userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        // 운영자인가요? 확인하기
        if(user.getRole()== UserRoleEnum.ADMIN) {
            boardRepository.deleteById(id);
            return "삭제완료";
        }
        else {
            System.out.println("관리자 아님!");
            return null;
        }
    }

}