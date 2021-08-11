package com.ssafy.api.controller;

import com.ssafy.api.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/livechat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.LEAVE.equals(message.getType()))
            message.setMessage(message.getSender() + "방송이 종료되었습니다.");
        if (ChatMessage.MessageType.ONE.equals(message.getType())) {
            //채팅 db에 저장

        }
        messagingTemplate.convertAndSend("/sub/livechat/room/" + message.getRoomId(), message);
    }


}
