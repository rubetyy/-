package com.ssafy.api.response.dto.Chatroom;

import com.ssafy.db.entity.Chatroom;
import com.ssafy.db.entity.Image;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
public class ChatroomResponseDto {

    int chatroompk;

    Integer productpk;

    String buyerid;

    String buyernickname;

    int buy;

    String sellernickname;

    public ChatroomResponseDto(Chatroom chatroom , String buyernickname, String sellernickname){
        this.chatroompk = chatroom.getChatroompk();
        this.productpk = chatroom.getProductpk();
        this.buyerid = chatroom.getUseridbuyer();
        this.buyernickname = buyernickname;
        this.buy = chatroom.getBuy();
        this.sellernickname = sellernickname;
    }

}
