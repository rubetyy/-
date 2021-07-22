package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ProductRegisterPostReq")
public class ProductRegisterPostReq {
    @ApiModelProperty(name="제목", example="일리윤 울트라 리페어 로션 새상품 판매합니다")
    String title;
    @ApiModelProperty(name="상품 설명", example="미개봉 새제품입니다")
    String content;
    @ApiModelProperty(name="상품 카테고리", example="3")
    Integer category;
    @ApiModelProperty(name="상품 가격", example="5500")
    Integer price;
    @ApiModelProperty(name="이미지 경로", example="C:/Users/KHW/Desktop/사무실.jpg")
    String thumbnail;

}
