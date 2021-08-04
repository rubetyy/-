package com.ssafy.db.repository.Live;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.LiveTitlePatchReq;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class LiveRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QLive qLive = QLive.live;
    QUser qUser = QUser.user;

    public Tuple findByLiveId(int liveid){
        Tuple live =  jpaQueryFactory.select(qLive,qUser)
                .from(qLive)
                .join(qUser).on(qLive.userid.eq(qUser.userid)).where(qLive.livepk.eq(liveid))
                .fetchOne();
        System.out.println(live.get(0,Live.class).getLivepk());
        System.out.println(live.get(1,User.class).getUserid());
        return live;
    }

    public Live findMaxIdx(){
        Live live = jpaQueryFactory.select(qLive).from(qLive).orderBy(qLive.livepk.desc()).fetchFirst();
        return live;
    }

//    public Long endLive(String value){
//        Long a = jpaQueryFactory.update(qLive).set(qLive.liveon,1)
//                .set(qLive.live_fin_date, Timestamp.valueOf(LocalDateTime.now()))
//                .where(qLive.userid.eq(value)).execute();
//        return a;
//    }

    public Long updatetitleLive(LiveTitlePatchReq liveTitlePatchReq){
        Long a =  jpaQueryFactory.update(qLive).set(qLive.livetitle, liveTitlePatchReq.getLivetitle())
                .where( qLive.livepk.eq( liveTitlePatchReq.getLivepk() ) ).execute();
        return a;
    }

}
