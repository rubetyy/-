package com.ssafy.db.entity;

import com.ssafy.api.request.dto.Chat.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="chat_message")
public class Message {
    @Id
    @Column(name = "chat_message_pk")
    int id;

    @Column(name = "chat_room_pk")
    int roomId;

    @Column(name = "sender_nickname")
    String sender;

    @Column(name = "chat_message_content")
    String content;

    @Column(name = "chat_message_created_at")
    LocalDateTime now;

    public Message(ChatMessage message){
        this.roomId = Integer.valueOf(message.getRoomId());
        this.sender = message.getSender();
        this.content = message.getMessage();
        this.now = LocalDateTime.now();

    }
}
