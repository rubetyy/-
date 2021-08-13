package com.ssafy.db.repository.Chatroom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.dto.Chat.ChatRoomReq;
import com.ssafy.api.response.dto.Chatroom.ChatroomResponseDto;
import com.ssafy.db.entity.Chatroom;
import com.ssafy.db.entity.QChatroom;
import com.ssafy.db.entity.QUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QChatroom qChatroom = QChatroom.chatroom;
    QUser qUser = QUser.user;

    public ChatroomResponseDto findMaxidx(){
        Chatroom c = jpaQueryFactory.select(qChatroom).from(qChatroom)
                .orderBy(qChatroom.chatroompk.desc()).fetchFirst();
        String buyernickname = jpaQueryFactory.select(qUser.usernickname).from(qUser)
                .where(qUser.userid.eq(c.getUseridbuyer())).fetchFirst();
        String sellernickname = jpaQueryFactory.select(qUser.usernickname).from(qUser)
                .where(qUser.userid.eq(c.getUseridseller())).fetchFirst();
        return new ChatroomResponseDto(c,buyernickname,sellernickname);
    }

    public boolean check(ChatRoomReq chatRoomReq){
        Chatroom c = jpaQueryFactory.select(qChatroom).from(qChatroom)
                .where(qChatroom.chatroompk.eq(chatRoomReq.getChatroompk())).fetchFirst();
        boolean flag = false;
        if(c.getUseridbuyer().equals(chatRoomReq.getUserid())) flag = true;
        if(c.getUseridseller().equals(chatRoomReq.getUserid())) flag = true;
        return flag;
    }



}