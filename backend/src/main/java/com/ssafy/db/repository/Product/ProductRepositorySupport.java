package com.ssafy.db.repository.Product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Product;
import com.ssafy.db.entity.QProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class ProductRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QProduct qProduct = QProduct.product;

    public void addViewCount(Long productId){
        Product p = jpaQueryFactory.select(qProduct).from(qProduct).where(qProduct.id.eq(productId)).fetchOne();
        Integer viewCount = p.getViewCount();
        long a = jpaQueryFactory.update(qProduct).set(qProduct.viewCount,viewCount+1).where(qProduct.id.eq(productId)).execute();
        System.out.println(a);
    }

}
