package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
@Table(name="file")
public class Image{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer imageId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_pk")
    private Product product;

    @Column(name = "origin_file_name")
    private String originFileName;

    private String filePath;

    private Long fileSize;

    @Builder
    public Image(String originFileName, String filePath, Long fileSize){
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
    //상품 정보 저장
    public void setProduct(Product product){
        this.product = product;
        //상품에 현재 이미지가 존재하지 않는다면 (무한루프 빠지지 않게 체크)
        if(!product.getImage().contains(this)){
            //이미지 추가
            product.getImage().add(this);
        }

    }
}
