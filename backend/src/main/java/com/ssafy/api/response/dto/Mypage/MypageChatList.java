package com.ssafy.api.response.dto.Mypage;

import com.ssafy.db.entity.Chatroom;
import lombok.Data;

@Data
public class MypageChatList {
    Integer chatroompk;
    Integer productpk;
    String useridbuyer;
    Integer buy;
    String useridseller;
    String buyernickname;
    String sellernickname;
    public MypageChatList(Chatroom chatroom){
        this.chatroompk = chatroom.getChatroompk();
        this.productpk = chatroom.getProductpk();
        this.useridbuyer = chatroom.getUseridbuyer();
        this.buy = chatroom.getBuy();
        this.useridseller = chatroom.getUseridseller();
    }
}
