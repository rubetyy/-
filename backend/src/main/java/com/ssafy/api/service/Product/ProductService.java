package com.ssafy.api.service.Product;

import com.ssafy.api.request.ProductDeleteReq;
import com.ssafy.api.request.ProductPatchReq;
import com.ssafy.api.request.ProductRegisterPostReq;
import com.ssafy.db.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRegisterPostReq productRegisterPostReq);
    List<Product> getProducts(String productId);
    long updateProduct(ProductPatchReq productPatchReq);
    long deleteProduct(ProductDeleteReq productDeleteReq);
}
