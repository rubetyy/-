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
@ToString
@Table(name="chat_message")
public class Message {
    @Id
    @Column("chat_message_pk")
    Integer id;

    @Column("chat_room_pk")
    Integer roomId;

    @Column("user_id_sender")
    String sender;

    @Column("chat_message_content")
    String content;

    @Column("chat_message_created_at")
    LocalDateTime now;

    public Message(ChatMessage message){
        this.roomId = Integer.valueOf(message.getRoomId());
        this.sender = message.getSender();
        this.content = message.getMessage();
        this.now = LocalDateTime.now();

    }
}
