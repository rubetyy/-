package com.ssafy.api.controller;

import com.ssafy.api.request.dto.Product.ProductDeleteReq;
import com.ssafy.api.request.dto.Product.ProductRegisterPostReq;
import com.ssafy.api.request.dto.Product.ProductPatchReq;
import com.ssafy.api.service.FileHandler.FileHandlerService;
import com.ssafy.api.service.Product.ProductService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Image;
import com.ssafy.db.entity.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Api(value = "상품관리 API", tags = {"Product"})
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    FileHandlerService fileHandlerService;

    @PostMapping("/create")
    public ResponseEntity registerProduct(
            @ModelAttribute ProductRegisterPostReq productRegisterPostReq,
            @RequestParam("images") List<MultipartFile> images,
            Authentication authentication){

        /**
         * 토큰이 존재하는 경우 사용자 로그인 여부를 확인하여 상품 등록 dto에 넣어준다
         */
        //jwt 토큰을 받는다
        if(Objects.isNull(authentication)){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "토큰이 존재하지 않습니다."));
        }

        SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
        String userId = userDetails.getUsername();
        //request dto에 사용자 아이디를 넣어주고
        productRegisterPostReq.setUserId(userId);
        //상품 생성 서비스에 넣어서 보낸다

        Product product = productService.createProduct(productRegisterPostReq,images);

        return new ResponseEntity(product,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProducts(@PathVariable String productId, HttpServletResponse response) throws IOException {
//        Product product = productService.getProductByProductId(Long.valueOf(productId));
//        List<Resource> images = fileHandlerService.download(Long.valueOf(productId));
        List<Image> images = fileHandlerService.download(Long.valueOf(productId));
//        Resource r = images.get(0);
//        String contentType = "image/png";

//        System.out.println(images.toString());


        String absolutePath = new File("").getAbsolutePath() + File.separator;
        List<String> imagelist = new ArrayList<String>();
        for(Image i : images){
                String filePath = absolutePath + i.getFilePath();

                i.setFilePath(filePath);
//            HttpServletResponse res = response;
////            res.reset();
//            OutputStream out = res.getOutputStream();
//            res.setHeader("Cache-Control","no-cache");
//            String downFile = absolutePath + i.getFilePath();
//            File file = new File(downFile);
//            res.addHeader("Content-disposition","attachment; fileName = " + i.getOriginFileName() + ".png");
//            res.setContentType(contentType);
//            FileInputStream in = new FileInputStream(file);
//            byte[] buffer = new byte[1024 * 8];
//
//            while(true){
//                int count = in.read(buffer);
//                if(count == -1) break;
//                out.write(buffer, 0 ,count);
//            }
//            in.close();
//            out.close();
        }



        Map<String,Object> res = new HashMap<String,Object>();
//        res.put("productInfo",product);
        res.put("images",images);
        return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
//                .body(res);
    }

    @PatchMapping()
    public ResponseEntity<? extends BaseResponseBody> updateProduct(@RequestBody ProductPatchReq productPatchReq){
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @DeleteMapping()
    public ResponseEntity<? extends BaseResponseBody> deleteProduct(@RequestBody ProductDeleteReq productDeleteReq){
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/main")
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
