package com.ssafy.db.repository.Wish;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class WishRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QWish qWish = QWish.wish;

    public long deleteWishProduct(int wishproductid){
        long a = jpaQueryFactory.delete(qWish).where(qWish.wishproductpk.eq(wishproductid))
                .execute();
        return a;
    }

    public Wish findWish(int productId, String userid){
        Wish wish = jpaQueryFactory.select(qWish).from(qWish).where(qWish.productpk.eq(productId),qWish.useridbuyer.eq(userid))
                .fetchFirst();
        return wish;
    }


}
