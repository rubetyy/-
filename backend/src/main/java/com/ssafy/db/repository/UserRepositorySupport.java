package com.ssafy.db.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport{
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;
    QProduct qProduct = QProduct.product;

    public Optional<User> findUserByUserId(String userId) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userid.eq(userId)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }

    public Optional<User> deleteUser(String userId){
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userid.eq(userId)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }

    public long updateUser(User user,String userId){
        long a = jpaQueryFactory.update(qUser).set(qUser.usernickname , user.getUsernickname())
                .where(qUser.userid.eq(userId)).execute();
        return a;
    }

    public User findMaxIdx(){
        User user = jpaQueryFactory.select(qUser).from(qUser).orderBy(qUser.userCreateAt.desc()).fetchFirst();
        return user;
    }


}
