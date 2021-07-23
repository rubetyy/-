package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
public class Product extends BaseEntity{

    private String user_id;
    private Integer live_id;
    private Integer category_id;
    private String p_title;
    private String p_description;
    private Integer p_price;
    private Date p_created_at;
    private Integer p_view_count;
    private String p_is_sold;
    private String p_live_status;

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
