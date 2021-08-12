package com.ssafy.db.repository.Chatroom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Chatroom;
import com.ssafy.db.entity.QChatroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QChatroom qChatroom = QChatroom.chatroom;

    public Chatroom findMaxidx(){
        return jpaQueryFactory.select(qChatroom).from(qChatroom)
                .orderBy(qChatroom.chatroompk.desc()).fetchFirst();
    }


}