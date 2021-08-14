package com.ssafy.db.repository.Live;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.dto.Live.LiveTitlePatchReq;
import com.ssafy.api.response.dto.Live.LiveCategoryDto;
import com.ssafy.api.response.dto.Live.LiveMainDto;
import com.ssafy.api.response.dto.Live.LiveSearchDto;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.Image.ImageRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;


/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class LiveRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QLive qLive = QLive.live;
    QUser qUser = QUser.user;
    QProduct qProduct = QProduct.product;

    @Autowired
    ImageRepositorySupport fileRepositorySupport;

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

    public Long endLive(int value){
        //Live테이블에 islive0값으로 update
        Long a = jpaQueryFactory.update(qLive).set(qLive.islive,0)
                .where(qLive.livepk.eq(value)).execute();
        //Product 테이블에 isLive 0값으로 update
//        Long b = jpaQueryFactory.update(qProduct).set(qProduct.isLive,0)
//                .where(qProduct.liveId.eq(value)).execute();
        return a;
    }

    public Long updatetitleLive(LiveTitlePatchReq liveTitlePatchReq){
        Long a =  jpaQueryFactory.update(qLive).set(qLive.livetitle, liveTitlePatchReq.getLivetitle())
                .where( qLive.livepk.eq( liveTitlePatchReq.getLivepk() ) ).execute();
        return a;
    }

    public List<LiveMainDto> selectMain(){
        List<Live> l = jpaQueryFactory.select(qLive).from(qLive)
                .where(qLive.islive.eq(1))
                .orderBy(qLive.liveviewercount.desc()).limit(12).fetch();
        List<LiveMainDto> res = new LinkedList<>();
        for(Live i : l){
            String filepath = fileRepositorySupport.getFilePath(i.getProductpk());
            res.add(new LiveMainDto(i,filepath));
        }
        return res;
    }

    public List<LiveSearchDto> getSearchLives(String search){
        List<Live> l = jpaQueryFactory.select(qLive).from(qLive)
                .where(qLive.livetitle.contains(search))
                .orderBy(qLive.liveviewercount.desc()).fetch();
        List<LiveSearchDto> res = new LinkedList();
        for(Live li : l){
            String filepath = fileRepositorySupport.getFilePath(li.getProductpk());
            res.add(new LiveSearchDto(li,filepath));
        }
        return res;
    }

    public List<LiveCategoryDto> getLiveByCategoryId(int categoryid){
        List<Tuple> l = jpaQueryFactory.select(qLive,qProduct).from(qLive)
                .join(qProduct).on(qProduct.id.eq(qLive.productpk))
                .where(qProduct.isSold.eq(0),qProduct.categoryId.eq(categoryid)).fetch();
        List<LiveCategoryDto> res = new LinkedList<>();
        for(Tuple t : l){
            Live live = t.get(0,Live.class);
            String filepath = fileRepositorySupport.getFilePath(live.getProductpk());
            res.add(new LiveCategoryDto(live,filepath));
        }

        return res;
    }

}
