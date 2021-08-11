package com.ssafy.api.service.Chat;


import com.ssafy.api.request.dto.Chat.ChatMessage;
import com.ssafy.db.entity.Chatroom;
import com.ssafy.db.entity.Message;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */

public interface ChatService {
    public Chatroom createChat(Chatroom chatroom);
    public Message talk(ChatMessage chatMessage);

}
