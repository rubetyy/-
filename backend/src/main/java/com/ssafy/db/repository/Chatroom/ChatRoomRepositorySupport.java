package com.ssafy.db.repository.Chatroom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Chatroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;



}