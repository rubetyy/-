package com.ssafy.api.service.Product;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.dto.Product.ProductDeleteReq;
import com.ssafy.api.request.dto.Product.ProductPatchReq;
import com.ssafy.api.request.dto.Product.ProductRegisterPostReq;
import com.ssafy.db.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRegisterPostReq productRegisterPostReq, List<MultipartFile> files);
    List<Product> getAllProducts(Long productId);
    List<Tuple> getMainProducts();
    List<Product> getProductsByCategory(Long categoryId);
    Product getProductByProductId(Long productId);
    long updateProduct(ProductPatchReq productPatchReq);
    long deleteProduct(ProductDeleteReq productDeleteReq);
}
