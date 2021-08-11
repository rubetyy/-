package com.ssafy.api.request.dto.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    public enum MessageType {
        ONE
    }
    private MessageType type;//ONE으로 올 예정
    private String roomId; //방번호
    private String seller; //판매자
    private String buyer; //구매자
    private String sender; //채팅한사람
    private String content;//채팅내용
    private Integer productpk;//상품pk
}
