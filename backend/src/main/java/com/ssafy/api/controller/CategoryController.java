package com.ssafy.api.controller;

import com.ssafy.api.response.dto.Product.ProductListResponseDto;
import com.ssafy.api.service.Product.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "카테고리탭에 속하는 화면 API", tags = {"Category"})
@RestController
@RequestMapping("/home")
public class CategoryController {
    
    @Autowired
    ProductService productService;

    @GetMapping()
    public List<ProductListResponseDto> getProductsByCategory(@RequestParam("categoryId") Long categoryId){
        List<ProductListResponseDto> productsByCategory = productService.getProductsByCategory(categoryId);
        return productsByCategory;
    }
}
