package com.ssafy.db.repository.Product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.response.dto.Product.ProductListRes;
import com.ssafy.db.entity.Product;
import com.ssafy.db.entity.QImage;
import com.ssafy.db.entity.QLive;
import com.ssafy.db.entity.QProduct;
import com.ssafy.db.repository.Image.ImageRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;


import com.querydsl.core.Tuple;

import java.util.LinkedList;
import java.util.List;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class ProductRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QProduct qProduct = QProduct.product;

    @Autowired
    ImageRepositorySupport fileRepositorySupport;

    public List<ProductListRes> findMain(){
        List<Product> pl = jpaQueryFactory.select(qProduct).from(qProduct)
                .where(qProduct.isSold.eq(0)).orderBy(qProduct.viewCount.desc())
                .limit(12).fetch();
        List<ProductListRes> mainlist = new LinkedList<>();
        for(Product p : pl){
            String filepath = fileRepositorySupport.getFilePath(p.getId());
            mainlist.add(new ProductListRes(p,filepath));
        }
        return mainlist;
    }

    public List<ProductListRes> findAllByCategoryId(int categoryid){
        List<Product> productList = jpaQueryFactory.select(qProduct).from(qProduct)
                .where(qProduct.categoryId.eq(categoryid)).orderBy(qProduct.viewCount.desc()).fetch();
        List<ProductListRes> categoryProductList = new LinkedList<>();
        for(Product p : productList){
            String filepath = fileRepositorySupport.getFilePath(p.getId());
            categoryProductList.add(new ProductListRes(p,filepath));
        }
        return categoryProductList;
    }

    public void addViewCount(int productId){
        Product p = jpaQueryFactory.select(qProduct).from(qProduct).where(qProduct.id.eq(productId)).fetchOne();
        Integer viewCount = p.getViewCount();
        long a = jpaQueryFactory.update(qProduct).set(qProduct.viewCount,viewCount+1).where(qProduct.id.eq(productId)).execute();
    }

    public List<ProductListRes> searchProduct(String search){
        List<Product> pl = jpaQueryFactory.select(qProduct).from(qProduct)
                .where(qProduct.title.contains(search)).orderBy(qProduct.viewCount.desc()).fetch();
        List<ProductListRes> searchList = new LinkedList<>();
        for(Product p : pl){
            String filepath = fileRepositorySupport.getFilePath(p.getId());
            searchList.add(new ProductListRes(p,filepath));
        }
        return searchList;
    }

    public Long setLivepk(int liveid,int productpk){
        //라이브 시작시 Product table에 live id업데이트, isLive 1값으로 업데이트
        Long a = jpaQueryFactory.update(qProduct).set(qProduct.isLive,1)
                .set(qProduct.liveId,liveid).where(qProduct.id.eq(productpk)).execute();
        return a;
    }

    public Long soldProduct(int productId){
        Long a = jpaQueryFactory.update(qProduct).set(qProduct.isSold,1)
                .where(qProduct.id.eq(productId)).execute();
        return a;
    }

    //product table is live 0,
    public Long endLive(int liveId){
        Long a = jpaQueryFactory.update(qProduct).set(qProduct.isLive,0)
                .set(qProduct.liveId,-1)
                .where(qProduct.liveId.eq(liveId)).execute();
        return a;
    }

}
