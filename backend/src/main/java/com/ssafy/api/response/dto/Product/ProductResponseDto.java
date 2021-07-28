package com.ssafy.api.response.dto.Product;

import com.ssafy.db.entity.Product;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductResponseDto {
    private Long productId;
    private String userId;
    private Integer liveId;
    private Long categoryId;
    private String title;
    private String description;
    private Integer price;
    private LocalDateTime createdAt;
    private Integer viewCount;
    private Integer isSold;
    private Integer isLive;

    public ProductResponseDto(Product entity) {
        this.productId = entity.getProductId();
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
    }
}
