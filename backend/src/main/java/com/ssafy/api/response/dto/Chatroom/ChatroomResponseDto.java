package com.ssafy.api.response.dto.Chatroom;

import com.ssafy.db.entity.Chatroom;
import lombok.Getter;

@Getter
public class ChatroomResponseDto {

    int chatroompk;

    Integer productpk;

    String buyernickname;

    int buy;

    String sellernickname;

    public ChatroomResponseDto(Chatroom chatroom , String buyernickname, String sellernickname){
        this.chatroompk = chatroom.getChatroompk();
        this.productpk = chatroom.getProductpk();
        this.buyernickname = buyernickname;
        this.buy = chatroom.getBuy();
        this.sellernickname = sellernickname;
    }

}
