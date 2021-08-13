package com.ssafy.api.service.Product;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.dto.Product.ProductDeleteReq;
import com.ssafy.api.request.dto.Product.ProductPatchReq;
import com.ssafy.api.request.dto.Product.ProductRegisterPostReq;
import com.ssafy.api.request.dto.Product.ProductWishReq;
import com.ssafy.api.response.dto.Product.ProductListRes;
import com.ssafy.db.entity.Product;
import com.ssafy.db.entity.Wish;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRegisterPostReq productRegisterPostReq, List<MultipartFile> files);
    List<ProductListRes> getSearchProducts(String search);
    List<ProductListRes> getMainProducts();
    List<ProductListRes> getProductsByCategory(int categoryId);
    Product getProductByProductId(int productId);
    void addViewCount(int productId);
    long soldProduct(int productId);
    long updateProduct(ProductPatchReq productPatchReq);
    long deleteProduct(ProductDeleteReq productDeleteReq);
    void addWishProduct(ProductWishReq wishReq);
    long deleteWishProduct(int wishproductid);
    Wish findWish(int productId, String userid);
}
