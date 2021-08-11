package com.ssafy.api.response.dto.Product;

import com.ssafy.db.entity.Image;
import com.ssafy.db.entity.Product;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
public class ProductListResponseDto {
    private Integer productId;
    private String userId;
    private Integer liveId;
    private Integer categoryId;
    private String title;
    private String description;
    private Integer price;
    private LocalDateTime createdAt;
    private Integer viewCount;
    private Integer isSold;
    private Integer isLive;
    private Image thumbnail;


    public ProductListResponseDto(Product entity) {
        this.productId = entity.getId();
        this.userId = entity.getUserId();
        this.liveId = entity.getLiveId();
        this.categoryId = entity.getCategoryId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.createdAt = entity.getCreatedAt();
        this.viewCount = entity.getViewCount();
        this.isSold = entity.getIsSold();
        this.isLive = entity.getIsLive();

        //이미지가 존재하면
        if(!entity.getImage().isEmpty()){
            this.thumbnail = entity.getImage().get(0); // 첫번째 이미지만 반환
        }

    }
}
