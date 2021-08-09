package com.ssafy.db.repository.Image;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QImage;
import com.ssafy.db.entity.QProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class ImageRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QImage qImage = QImage.image;
    QProduct qProduct = QProduct.product;

    public List<Tuple> findMain() {
        List<Tuple> l = jpaQueryFactory.select(qImage,qProduct).from(qImage)
                .join(qProduct).on(qProduct.id.eq(qImage.product.id))
                .distinct().orderBy(qProduct.viewCount.desc()).limit(12).fetch();

        return l;
    }

    public List<Tuple> findAllByCategoryId(Long categoryId){
        List<Tuple> l = jpaQueryFactory.select(qImage,qProduct).from(qImage)
                .join(qProduct).on(qProduct.id.eq(qImage.product.id))
                .where(qProduct.categoryId.eq(categoryId)).distinct().orderBy(qProduct.viewCount.desc()).limit(12).fetch();
        return l;
    }


}
