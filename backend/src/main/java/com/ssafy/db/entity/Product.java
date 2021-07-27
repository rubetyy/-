package com.ssafy.db.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
@Table(name="product")
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_pk")
    private Long productId;

    @Column(name="user_id_seller")
    private String userId;

    @Column(name = "live_id")
    private Integer liveId;

    @Column(name = "category_pk")
    private Integer categoryId;

    @Column(name = "product_title")
    private String title;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_price")
    private Integer price;

    @CreationTimestamp
    @Column(name = "product_created_at")
    private LocalDateTime createdAt;

    @Column(name = "product_view_count")
    private Integer viewCount;

    @Column(name = "product_is_sold")
    private Integer isSold;

    @Column(name = "product_is_live")
    private Integer isLive;

    @OneToMany(
            mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //상품삭제하면 상품이미지들도 같이 삭제된다
            orphanRemoval = true
    )
    private List<Image> image = new ArrayList<>();

    @Builder
    public Product(String userId, Integer liveId, Integer categoryId, String title, String description, Integer price, Integer viewCount, Integer isSold, Integer isLive) {
        this.userId = userId;
        this.liveId = liveId;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.viewCount = viewCount;
        this.isSold = isSold;
        this.isLive = isLive;
    }

    public void addImage(Image image){
        this.image.add(image);
        //상품등록시 이미지가 저장되어 있지 않은 경우
        if(image.getProduct() != this){
            image.setProduct(this);
        }
    }
}
