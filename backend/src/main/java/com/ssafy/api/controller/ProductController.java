package com.ssafy.api.controller;

import com.ssafy.api.request.ProductDeleteReq;
import com.ssafy.api.request.ProductRegisterPostReq;
import com.ssafy.api.request.ProductPatchReq;
import com.ssafy.api.service.Product.ProductService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Product;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "상품관리 API", tags = {"Product"})
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<? extends BaseResponseBody> registerProduct(@ModelAttribute ProductRegisterPostReq productRegisterPostReq){
        Product product = productService.createProduct(productRegisterPostReq);
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
