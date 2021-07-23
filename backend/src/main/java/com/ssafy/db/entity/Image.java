package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
@Table(name="file")
public class Image extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    private String origin_file_name;

    private String file_path;

    private Long file_size;

    @Builder
    public Image(String origin_file_name, String file_path, Long file_size){
        this.origin_file_name = origin_file_name;
        this.file_path = file_path;
        this.file_size = file_size;
    }

    public void setProduct(Product product){
        this.product = product;

    }
}
