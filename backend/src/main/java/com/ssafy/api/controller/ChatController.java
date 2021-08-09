package com.ssafy.api.controller;

import com.ssafy.api.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor){
        //누가 보냈는지 정보 담기
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }

//    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
//    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
//    @MessageMapping("/receive")
//    @SendTo("/send")
//    public ChatVO ChatHandler(ChatVO chatVO){
//        String userName = chatVO.getUsername();
//        String content = chatVO.getContent();
//
//        ChatVO result = new ChatVO(userName, content);
//        return result;
//    }

}
