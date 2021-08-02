package com.ssafy.api.controller;

import com.ssafy.api.response.dto.Product.ProductListResponseDto;
import com.ssafy.api.response.dto.User.UserRes;
import com.ssafy.api.service.Product.ProductService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.db.entity.Product;
import com.ssafy.db.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "메인페이지 API", tags = {"Home"})
@RestController
@RequestMapping("/main/home")
public class HomeController {

    @Autowired
    ProductService productService;

    @GetMapping()
    @ApiOperation(value = "상품 리스트 조회", notes = "메인페이지에서 상품정보 12개 조회.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity getUserInfo() {
        List<Product> l = productService.getMainProducts();

        return new ResponseEntity<List<Product>>(l, HttpStatus.OK);
    }



}
