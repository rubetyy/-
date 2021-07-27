package com.ssafy.api.controller;

import com.ssafy.api.request.dto.Product.ProductDeleteReq;
import com.ssafy.api.request.dto.Product.ProductRegisterPostReq;
import com.ssafy.api.request.dto.Product.ProductPatchReq;
import com.ssafy.api.response.dto.Product.ProductResponseDto;
import com.ssafy.api.service.FileHandler.FileHandlerService;
import com.ssafy.api.service.Product.ProductService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Product;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

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
/**
 * 토큰이 존재하는 경우 사용자 로그인 여부를 확인하여 상품 등록 dto에 넣어준다
 */
//        //jwt 토큰을 받는다
//        SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
//        if(Objects.isNull(userDetails)){
//            throw new RuntimeException("토큰이 존재하지 않습니다.");
//        }
//
//        String userId = userDetails.getUsername();
//        //request dto에 사용자 아이디를 넣어주고
//        productRegisterPostReq.setUserId(userId);
//        //상품 생성 서비스에 넣어서 보낸다
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
