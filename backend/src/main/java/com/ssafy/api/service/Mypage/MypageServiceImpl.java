package com.ssafy.api.service.Mypage;

import com.ssafy.api.request.ProductDeleteReq;
import com.ssafy.api.request.ProductPatchReq;
import com.ssafy.api.request.ProductRegisterPostReq;
import com.ssafy.db.entity.Product;

import java.util.List;

public class MypageServiceImpl implements  MypageService{
    @Override
    public Product createProduct(ProductRegisterPostReq productRegisterPostReq) {
        return null;
    }

    @Override
    public List<Product> getProducts(String productId) {
        return null;
    }

    @Override
    public long updateProduct(ProductPatchReq productPatchReq) {
        return 0;
    }

    @Override
    public long deleteProduct(ProductDeleteReq productDeleteReq) {
        return 0;
    }
}
