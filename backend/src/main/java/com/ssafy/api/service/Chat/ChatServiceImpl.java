package com.ssafy.api.service.Chat;

import com.ssafy.api.request.dto.Chat.ChatMessage;
import com.ssafy.db.entity.Chatroom;
import com.ssafy.db.entity.Message;
import com.ssafy.db.repository.Chatmessage.ChatMessageRepository;
import com.ssafy.db.repository.Chatroom.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("chatService")
public class ChatServiceImpl implements ChatService {

	@Autowired
	ChatRoomRepository chatRoomRepository;
	@Autowired
	ChatMessageRepository chatMessageRepository;
	@Override
	public Chatroom createChat(Chatroom chatroom) {
		return chatRoomRepository.save(chatroom);
	}

	@Override
	public Message talk(ChatMessage chatMessage) {
		Message m = new Message(chatMessage);
		return chatMessageRepository.save(m);
	}
}
