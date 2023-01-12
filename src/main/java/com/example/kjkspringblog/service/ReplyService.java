package com.example.kjkspringblog.service;

import com.example.kjkspringblog.dto.ReplyRequestDto;
import com.example.kjkspringblog.dto.ReplyResponseDto;
import com.example.kjkspringblog.entity.Reply;
import com.example.kjkspringblog.entity.User;
import com.example.kjkspringblog.entity.UserRoleEnum;
import com.example.kjkspringblog.jwt.JwtUtil;
import com.example.kjkspringblog.repository.ReplyRepository;
import com.example.kjkspringblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public ReplyResponseDto createReply(ReplyRequestDto replyRequestDto, HttpServletRequest request) {
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
        Reply reply =new Reply(replyRequestDto, user);
        replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }


    @Transactional(readOnly = true)
    public List<ReplyResponseDto> getReplys() {

        List<ReplyResponseDto> list = new ArrayList<>();

        List<Reply> replyList;
        replyList = replyRepository.findAllByOrderByCreatedAtDesc();

        for (Reply reply : replyList) {
            list.add(new ReplyResponseDto(reply));
        }

        return list;
    }

    @Transactional(readOnly = true)
    public ReplyResponseDto getReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지않음")
        );
        return new ReplyResponseDto(reply);
    }

    @Transactional
    public ReplyResponseDto updateReply(Long id, ReplyRequestDto requestDto, HttpServletRequest request) {
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
            Reply reply = replyRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("해당 데이터는 존재하지 않습니다.")
            );
            reply.update(requestDto);
            return new ReplyResponseDto(reply);
        }
        else { //아니면 나가!
            log.info("관리자 아님!");
            return null;
        }
    }

    @Transactional
    public String deleteReply(Long id, HttpServletRequest request) {
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
            replyRepository.deleteById(id);
            return "삭제완료";
        }
        else {
            log.info("관리자 아님!");
            return null;
        }
    }

}