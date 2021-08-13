package com.ssafy.api.service.Chat;


import com.ssafy.api.request.dto.Chat.ChatMessage;
import com.ssafy.api.request.dto.Chat.ChatRoomReq;
import com.ssafy.api.response.dto.Chatroom.ChatroomResponseDto;
import com.ssafy.db.entity.Chatroom;
import com.ssafy.db.entity.Message;
import java.util.List;
/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */

public interface ChatService {
    public ChatroomResponseDto createChat(Chatroom chatroom);
    public Message talk(ChatMessage chatMessage);
    public List<Message> selectAllChat(int chatroompk);
    public boolean check(ChatRoomReq chatRoomReq);

}
