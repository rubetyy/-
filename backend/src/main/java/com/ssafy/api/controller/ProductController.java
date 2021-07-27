package com.ssafy.api.controller;

import com.ssafy.api.request.dto.Product.ProductDeleteReq;
import com.ssafy.api.request.dto.Product.ProductRegisterPostReq;
import com.ssafy.api.request.dto.Product.ProductPatchReq;
import com.ssafy.api.response.dto.Product.ProductResponseDto;
import com.ssafy.api.service.FileHandler.FileHandlerService;
import com.ssafy.api.service.Product.ProductService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Product;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(value = "상품관리 API", tags = {"Product"})
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    FileHandlerService fileHandlerService;

    @PostMapping("/create")
    public ResponseEntity<? extends BaseResponseBody> registerProduct(
            @ModelAttribute ProductRegisterPostReq productRegisterPostReq,
            @RequestParam("images") List<MultipartFile> images){
        Product product = productService.createProduct(productRegisterPostReq,images);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProducts(@RequestParam("productId") Long productId, @RequestParam("userId") String userId){
        ProductResponseDto productResponseDto = productService.getProductByProductIdAndUserId(productId, userId);
        return ResponseEntity.status(200).body(productResponseDto);
    }

    @PatchMapping()
    public ResponseEntity<? extends BaseResponseBody> updateProduct(@RequestBody ProductPatchReq productPatchReq){
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @DeleteMapping()
    public ResponseEntity<? extends BaseResponseBody> deleteProduct(@RequestBody ProductDeleteReq productDeleteReq){
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }


}
