package com.ssafy.db.entity;

import com.ssafy.api.request.dto.Product.ProductWishReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="wish_product")
public class Wish {
    @Id
    @Column(name="wish_product_pk")
    int wishproductpk;

    @Column(name="product_pk")
    Integer productpk;

    @Column(name="user_id_buyer")
    String useridbuyer;

    public Wish(ProductWishReq wishReq){
        this.productpk = wishReq.getProductpk();
        this.useridbuyer = wishReq.getUseridbuyer();
    }
}
