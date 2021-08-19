package com.ssafy.api.request.dto.Product;

import com.ssafy.db.entity.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@ApiModel("ProductRegisterPostReq")
public class ProductRegisterPostReq {
    private String userId;

    @ApiModelProperty(name="제목", example="일리윤 울트라 리페어 로션 새상품 판매합니다")
    private String title;
    @ApiModelProperty(name="상품 설명", example="미개봉 새제품입니다")
    private String description;
    @ApiModelProperty(name="상품 카테고리", example="3")
    private Integer category;
    @ApiModelProperty(name="상품 가격", example="5500")
    private Integer price;
    @ApiModelProperty(name="상품 판매 여부", example="0")
    private Integer is_sold;
    @ApiModelProperty(name="라이브 방송 여부", example="1")
    private Integer is_live;

    @Builder
    public ProductRegisterPostReq(String userId, String title, String description, Integer category, Integer price, Integer is_sold, Integer is_live) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.is_sold = is_sold;
        this.is_live = is_live;
    }

    public Product toEntity(){
        return Product.builder()
                .userId(userId)
                .title(title)
                .description(description)
                .categoryId(category)
                .price(price)
                .isSold(is_sold)
                .isLive(is_live)
                .build();

    }
}
