package com.ssafy.api.controller;

import com.ssafy.api.response.dto.Live.LiveCategoryDto;
import com.ssafy.api.response.dto.Product.ProductListRes;
import com.ssafy.api.service.Live.LiveService;
import com.ssafy.api.service.Product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "카테고리탭에 속하는 화면 API", tags = {"Category"})
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ProductService productService;

    @Autowired
    LiveService liveService;

    @GetMapping("/{Id}")
    @ApiOperation(value = "카테고리 리스트 조회", notes = "카테고리페이지에서 상품정보 12개 조회.")
    public ResponseEntity getCategoryProducts(@PathVariable String Id) {
        Map<String,Object> res = new HashMap<>();

        List<ProductListRes> categoryProductList = productService.getProductsByCategory(Integer.valueOf(Id));
        List<LiveCategoryDto> categoryLiveList = liveService.getLiveByCategory(Integer.valueOf(Id));

        res.put("liveList",categoryLiveList);
        res.put("productList",categoryProductList);

        return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
    }

}
