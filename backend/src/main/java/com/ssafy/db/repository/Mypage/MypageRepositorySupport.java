package com.ssafy.db.repository.Mypage;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.response.dto.Mypage.MypagePRes;
import com.ssafy.db.entity.*;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class MypageRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;
    QProduct qProduct = QProduct.product;
    QChatroom qChatroom = QChatroom.chatroom;
    QWish qWish = QWish.wish;

    //하드코딩
    public Map<String,Object> mypageinfo(String userid){
        Map<String,Object> res = new HashMap<String,Object>();
        //userinfo
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userid.eq(userid)).fetchOne();
        Map<String,Object> u = new HashMap<>();
        u.put("userid",user.getUserid());
        u.put("usernickname",user.getUsernickname());
        res.put("userinfo",u);

        //issold
        List<Product> issold = (List<Product>)jpaQueryFactory.select(qProduct).from(qProduct)
                .where(qProduct.isSold.eq(1),qProduct.userId.eq(userid)).fetch();
        List<MypagePRes> issoldlist = new ArrayList<MypagePRes>();
        System.out.println(issold.get(0));
//        for(Product p : issold){
//            MypagePRes pres = new MypagePRes();
//            pres.setProductpk(p.getProductId());
//            pres.setProducttitle(p.getTitle());
//            issoldlist.add(pres);
//        }
        res.put("issold",issold);

        //soldout
        List<Product> soldout =(List<Product>) jpaQueryFactory.select(qProduct).from(qProduct)
                .where(qProduct.isSold.eq(0),qProduct.userId.eq(userid)).fetch();
        List<MypagePRes> soldoutlist = new ArrayList<MypagePRes>();
        for(Product p : soldout){
            MypagePRes pres = new MypagePRes();
            pres.setProductpk(p.getProductId());
            pres.setProducttitle(p.getTitle());
            soldoutlist.add(pres);
        }
        res.put("soldout",soldoutlist);

        //orderlist
        List<Chatroom> orderlistc = (List<Chatroom>) jpaQueryFactory.select(qChatroom).from(qChatroom)
                .where(qChatroom.useridbuyer.eq(userid),qChatroom.buy.eq(Long.valueOf(1))).fetch();
        List<MypagePRes> orderlist = new ArrayList<>();
        for(Chatroom c : orderlistc){
            Long pk = c.getProductpk();
            Product order = jpaQueryFactory.select(qProduct).from(qProduct)
                    .where(qProduct.productId.eq(pk)).fetchOne();
            MypagePRes pres = new MypagePRes();
            pres.setProductpk(order.getProductId());
            pres.setProducttitle(order.getTitle());
            orderlist.add(pres);
        }
        res.put("orderlist",orderlist);

        //wishlist
        List<Wish> wishres = (List<Wish>) jpaQueryFactory.select(qWish).from(qWish)
                .where(qWish.useridbuyer.eq(userid)).fetch();
        List<MypagePRes> wishlist = new ArrayList<>();
        for(Wish w : wishres){
            Long pk = w.getWishproductpk();
            Product wish = jpaQueryFactory.select(qProduct).from(qProduct)
                    .where(qProduct.productId.eq(pk)).fetchOne();
            MypagePRes wishp = new MypagePRes();
            wishp.setProductpk(wish.getProductId());
            wishp.setProducttitle(wish.getTitle());
            wishlist.add(wishp);
        }
        res.put("wishlist",wishlist);

        //chatlist
        List<Chatroom> chatres = (List<Chatroom>) jpaQueryFactory.select(qChatroom).from(qChatroom)
                .where(qChatroom.useridbuyer.eq(userid)).fetch();
        res.put("chatlist",chatres);
        return res;
    }
}
