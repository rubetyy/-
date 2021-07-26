package com.ssafy.db.repository.Live;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.LiveUpdate_titlePatchReq;
import com.ssafy.db.entity.Live;
import com.ssafy.db.entity.QLive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.LocalTime.now;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class LiveRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QLive qLive = QLive.live;

    public Live findByLiveId(int liveid){
        Live live = jpaQueryFactory.select(qLive).from(qLive)
                .where(qLive.live_pk.eq(Integer.valueOf(liveid))).fetchOne();

        return live;
    }

//    public Long endLive(String value){
//        Long a = jpaQueryFactory.update(qLive).set(qLive.liveon,1)
//                .set(qLive.live_fin_date, Timestamp.valueOf(LocalDateTime.now()))
//                .where(qLive.userid.eq(value)).execute();
//        return a;
//    }

    public Long updatetitleLive(LiveUpdate_titlePatchReq liveUpdate_titlePatchReq){
        Long a =  jpaQueryFactory.update(qLive).set(qLive.live_title,liveUpdate_titlePatchReq.getLivetitle())
                .where( qLive.live_pk.eq( Integer.valueOf(liveUpdate_titlePatchReq.getId()) ) ).execute();
        return a;
    }

}
