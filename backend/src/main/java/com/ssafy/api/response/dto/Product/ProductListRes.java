package com.ssafy.api.response.dto.Product;

import com.ssafy.db.entity.Product;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProductListRes {

    private Integer id;
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
    private String filepath;

    public ProductListRes(Product p , String filepath){
        this.id = p.getId();
        this.userId = p.getUserId();
        this.liveId = p.getLiveId();
        this.categoryId = p.getCategoryId();
        this.title = p.getTitle();
        this.description = p.getDescription();
        this.price = p.getPrice();
        this.createdAt = p.getCreatedAt();
        this.viewCount = p.getViewCount();
        this.isSold = p.getIsSold();
        this.isLive = p.getIsLive();
        this.filepath = filepath;
    }
}
