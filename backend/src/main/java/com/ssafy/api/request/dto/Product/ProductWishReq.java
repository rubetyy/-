package com.ssafy.api.request.dto.Product;

import lombok.Data;

@Data
public class ProductWishReq {
    String useridbuyer;
    int productpk;
}
