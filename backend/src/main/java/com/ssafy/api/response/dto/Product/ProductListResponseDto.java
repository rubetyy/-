package com.ssafy.api.response.dto.Product;

import com.ssafy.db.entity.Product;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ProductListResponseDto {
    private Long id;
    private String userId;
    private Integer liveId;
    private Integer categoryId;
    private String title;
    private String description;
    private Integer price;
    private LocalDateTime createdAt;
    private Integer viewCount;
    private Integer isSold;
    private Integer liveStatus;


    public ProductListResponseDto(Product entity) {
        this.id = entity.getProductId();
        this.userId = entity.getUserId();
        this.liveId = entity.getLiveId();
        this.categoryId = entity.getCategoryId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.createdAt = entity.getCreatedAt();
        this.viewCount = entity.getViewCount();
        this.isSold = entity.getIsSold();
        this.liveStatus = entity.getLiveStatus();
    }
}
