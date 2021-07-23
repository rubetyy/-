package com.ssafy.api.service.Product;

import com.ssafy.api.request.ProductDeleteReq;
import com.ssafy.api.request.ProductPatchReq;
import com.ssafy.api.request.ProductRegisterPostReq;
import com.ssafy.db.entity.Product;
import com.ssafy.db.repository.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRegisterPostReq productRegisterPostReq) {
        Product product = new Product();

        product.setP_title(productRegisterPostReq.getTitle());
        product.setP_description(productRegisterPostReq.getDescription());
        product.setCategory_id(productRegisterPostReq.getCategory());
        product.setP_price(productRegisterPostReq.getPrice());
        product.setP_is_sold(productRegisterPostReq.getIs_sold());
        product.setP_live_status(productRegisterPostReq.getLive_status());

        return productRepository.save(product);
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
