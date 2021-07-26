package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "live_id")
    private Integer liveId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "p_title")
    private String title;

    @Column(name = "p_description")
    private String description;

    @Column(name = "p_price")
    private Integer price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "is_sold")
    private Integer isSold;

    @Column(name = "live_status")
    private Integer liveStatus;

    @OneToMany(
            mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //상품삭제하면 상품이미지들도 같이 삭제된다
            orphanRemoval = true
    )
    private List<Image> image = new ArrayList<>();

    public void addImage(Image image){
        this.image.add(image);
        //상품등록시 이미지가 저장되어 있지 않은 경우
        if(image.getProduct() != this){
            image.setProduct(this);
        }
    }
}
