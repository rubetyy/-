package com.ssafy.api.request.dto.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK, LEAVE, ONE
    }

    private MessageType type; // 메시지 타입
    private String roomId; // 방번호//chatroompk
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}
