package com.ssafy.api.service.Product;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.dto.Product.ProductDeleteReq;
import com.ssafy.api.request.dto.Product.ProductPatchReq;
import com.ssafy.api.request.dto.Product.ProductRegisterPostReq;
import com.ssafy.api.service.FileHandler.FileHandlerService;
import com.ssafy.db.entity.Image;
import com.ssafy.db.entity.Product;
import com.ssafy.db.repository.Image.ImageRepository;
import com.ssafy.db.repository.Image.ImageRepositorySupport;
import com.ssafy.db.repository.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository fileRepository;
    @Autowired
    private FileHandlerService fileHandlerService;
    @Autowired
    private ImageRepositorySupport fileRepositorySupport;

    @Override
    public Product createProduct(ProductRegisterPostReq productRegisterPostReq, List<MultipartFile> files) {

        Product product = new Product()
                .builder()
                .userId(productRegisterPostReq.getUserId())
                .title(productRegisterPostReq.getTitle())
                .description(productRegisterPostReq.getDescription())
                .categoryId(productRegisterPostReq.getCategory())
                .price(productRegisterPostReq.getPrice())
                .isSold(productRegisterPostReq.getIs_sold())
                .isLive(productRegisterPostReq.getIs_live())
                .build();

        List<Image> images = fileHandlerService.upload(files);

        if(!images.isEmpty()){
            //파일 정보 db 저장
            for(Image image : images){
                product.addImage(fileRepository.save(image));
            }
        }

        productRepository.save(product);

        return productRepository.findTop1ByOrderByIdDesc();
    }

    /**
     * 카테고리에 속한 상품 전체 조회
     * */
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(Long categoryId){
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts(Long productId) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tuple> getMainProducts(){
        return fileRepositorySupport.findMain();
    }

    @Override
    public Product getProductByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return product;
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
