package com.ssafy.api.service.Product;

import com.ssafy.api.request.ProductDeleteReq;
import com.ssafy.api.request.ProductPatchReq;
import com.ssafy.api.request.ProductRegisterPostReq;
import com.ssafy.api.response.dto.Product.ProductResponseDto;
import com.ssafy.api.service.FileHandler.FileHandlerService;
import com.ssafy.db.entity.Image;
import com.ssafy.db.entity.Product;
import com.ssafy.db.repository.Image.ImageRepository;
import com.ssafy.db.repository.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository fileRepository;
    @Autowired
    private FileHandlerService fileHandlerService;

    @Override
    public Product createProduct(ProductRegisterPostReq productRegisterPostReq, List<MultipartFile> files) {
        Product product = new Product();

        product.setTitle(productRegisterPostReq.getTitle());
        product.setDescription(productRegisterPostReq.getDescription());
        product.setCategoryId(productRegisterPostReq.getCategory());
        product.setPrice(productRegisterPostReq.getPrice());
        product.setIsSold(productRegisterPostReq.getIs_sold());
        product.setLiveStatus(productRegisterPostReq.getLive_status());

        List<Image> images = fileHandlerService.upload(files);

        if(!images.isEmpty()){
            //파일 정보 db 저장
            for(Image image : images){
                product.addImage(fileRepository.save(image));
            }
        }

        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts(Long productId) {
        return null;
    }

    @Override
    public ProductResponseDto getProductByProductIdAndUserId(Long productId, String userId) {
        Product product = productRepository.findByProductIdAndUserId(productId, userId).orElse(null);
        return new ProductResponseDto(product);
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
