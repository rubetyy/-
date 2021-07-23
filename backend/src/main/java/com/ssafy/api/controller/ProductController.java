package com.ssafy.api.controller;

import com.ssafy.api.request.ProductDeleteReq;
import com.ssafy.api.request.ProductRegisterPostReq;
import com.ssafy.api.request.ProductPatchReq;
import com.ssafy.api.service.FileHandler.FileHandlerService;
import com.ssafy.api.service.Product.ProductService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Product;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;

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
            @RequestParam("images") MultipartFile images){
        Product product = productService.createProduct(productRegisterPostReq);
        fileHandlerService.upload(images);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<? extends BaseResponseBody> getProducts(@PathVariable("productId") String productId){

        return null;
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
