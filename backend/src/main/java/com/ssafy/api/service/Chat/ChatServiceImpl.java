package com.ssafy.api.service.Chat;

import com.ssafy.api.request.dto.Chat.ChatMessage;
import com.ssafy.api.response.dto.Chatroom.ChatroomResponseDto;
import com.ssafy.db.entity.Chatroom;
import com.ssafy.db.entity.Message;
import com.ssafy.db.repository.Chatmessage.ChatMessageRepository;
import com.ssafy.db.repository.Chatmessage.ChatMessageRepositorySupport;
import com.ssafy.db.repository.Chatroom.ChatRoomRepository;
import com.ssafy.db.repository.Chatroom.ChatRoomRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("chatService")
public class ChatServiceImpl implements ChatService {

	@Autowired
	ChatRoomRepository chatRoomRepository;
	@Autowired
	ChatRoomRepositorySupport chatRoomRepositorySupport;
	@Autowired
	ChatMessageRepository chatMessageRepository;
	@Autowired
	ChatMessageRepositorySupport chatMessageRepositorySupport;

	@Override
	public ChatroomResponseDto createChat(Chatroom chatroom) {
		chatRoomRepository.save(chatroom);
		return chatRoomRepositorySupport.findMaxidx();
	}

	@Override
	public Message talk(ChatMessage chatMessage) {
		Message m = new Message(chatMessage);
		return chatMessageRepository.save(m);
	}

	@Override
	public List<Message> selectAllChat(int chatroompk) {
		return chatMessageRepositorySupport.selectAllChat(chatroompk);
	}
}
