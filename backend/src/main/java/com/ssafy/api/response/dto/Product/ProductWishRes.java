package com.ssafy.api.response.dto.Product;

import com.ssafy.db.entity.Wish;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductWishRes {
    int wishproductpk;
    boolean flag;

    public ProductWishRes (){}
}
