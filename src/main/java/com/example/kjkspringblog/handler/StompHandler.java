package com.example.kjkspringblog.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        if(message.getHeaders().containsValue("ㅅㅂ")){
            return new Message<Object>() {
                @Override
                public Object getPayload() {
                    return null;
                }

                @Override
                public MessageHeaders getHeaders() {
                    return null;
                }
            };
        }
        return message;

    }
}
