package com.ssafy.api.controller;

import com.querydsl.core.Tuple;
import com.ssafy.api.service.Product.ProductService;
import com.ssafy.db.entity.Image;
import com.ssafy.db.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "카테고리탭에 속하는 화면 API", tags = {"Category"})
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ProductService productService;

    @GetMapping("/{Id}")
    @ApiOperation(value = "카테고리 리스트 조회", notes = "카테고리페이지에서 상품정보 12개 조회.")
    public ResponseEntity getCategoryProducts(@PathVariable String Id) {
        Map<String,Object> res = new HashMap<>();
        List<Tuple> l = productService.getProductsByCategory(Long.valueOf(Id));
        int cnt = 0;
        List<Image> il = new ArrayList<>();
        for(Tuple t : l){
            Image image = t.get(0,Image.class);
            il.add(image);
        }
        res.put("liveList",null);
        res.put("productList",il);

        return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
    }

}
