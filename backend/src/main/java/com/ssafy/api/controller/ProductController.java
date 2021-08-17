package com.ssafy.api.controller;

import com.querydsl.core.Tuple;
import com.ssafy.api.response.dto.Live.LiveMainDto;
import com.ssafy.api.request.dto.Product.*;
import com.ssafy.api.response.dto.Live.LiveSearchDto;
import com.ssafy.api.response.dto.Product.ProductListRes;
import com.ssafy.api.response.dto.Product.ProductWishRes;
import com.ssafy.api.service.Chat.ChatServiceImpl;
import com.ssafy.api.service.FileHandler.FileHandlerService;
import com.ssafy.api.service.Live.LiveService;
import com.ssafy.api.service.Product.ProductService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;

@Api(value = "상품관리 API", tags = {"Product"})
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    FileHandlerService fileHandlerService;
    @Autowired
    LiveService liveService;
    @Autowired
    ChatServiceImpl chatService;

    @Autowired
    UserService userService;
    //검색기능 변환완료
    @PostMapping("/search")
    public ResponseEntity searchProduct(@RequestBody ProductSearchReq searchReq){
        String search = searchReq.getSearch();
        List<LiveSearchDto> liveSearchList = liveService.getSearchLives(search);
        List<ProductListRes> productSearchList = productService.getSearchProducts(search);
        Map<String,Object> res = new HashMap<>();
        res.put("liveList",liveSearchList);
        res.put("productList",productSearchList);
        return new ResponseEntity(res,HttpStatus.OK);
    }

    //물건 판매 처리
    @Transactional
    @PostMapping("/sold")
    public ResponseEntity soldProduct(@RequestBody ProductSoldReq productSoldReq){
        int productId = productSoldReq.getProductpk();
        //판매완료 product table에 완료
        Long a = productService.soldProduct(productId);
        //chatroom에 chatroomisbuyer -> 1로 바꿈
        chatService.sold(productSoldReq);
        return new ResponseEntity(a,HttpStatus.OK);
    }

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
        productRegisterPostReq.setDescription(productRegisterPostReq.getDescription().replace("\r\n","<br>"));

        Product product = productService.createProduct(productRegisterPostReq,images);

        return new ResponseEntity(product,HttpStatus.OK);
    }

    //완료
    @Transactional//조회수 1증가 (update실행)
    @PostMapping("/detail")
    public ResponseEntity getProducts(@RequestBody ProductDetailReq detailReq) {
        int productId = detailReq.getProductpk();
        List<Image> images = fileHandlerService.download(Integer.valueOf(productId));
        String nickname = null;
        for(Image i : images){
                User u = userService.getUserByUserId(i.getProduct().getUserId());
                nickname = u.getUsernickname();
        }
        productService.addViewCount(Integer.valueOf(productId));
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("usernickname",nickname);
        res.put("images",images);

        ProductWishRes wishRes = new ProductWishRes();
        res.put("wish",wishRes);
        if(detailReq.getUserid() != null){
            Wish w = productService.findWish(productId,detailReq.getUserid());
            if(w == null) wishRes.setFlag(false);
            else {
                wishRes.setWishproductpk(w.getWishproductpk());
                wishRes.setFlag(true);
            }
        }
        return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<? extends BaseResponseBody> updateProduct(@RequestBody ProductPatchReq productPatchReq){
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @DeleteMapping()
    public ResponseEntity<? extends BaseResponseBody> deleteProduct(@RequestBody ProductDeleteReq productDeleteReq){
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    //완료
    @GetMapping("/main")
    @ApiOperation(value = "상품 리스트 조회", notes = "메인페이지에서 상품정보 12개 조회.")
    public ResponseEntity getMainInfo() {
        Map<String,Object> res = new HashMap<>();
        List<ProductListRes> productMainList = productService.getMainProducts();
        List<LiveMainDto> liveMainList = liveService.selectMain();
        res.put("liveList",liveMainList);
        res.put("productList",productMainList);
        return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
    }

    //완료
    @PostMapping("/wish")
    public ResponseEntity wishProduct(@RequestBody ProductWishReq wishproduct) {
        productService.addWishProduct(wishproduct);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    //완료
    @Transactional
    @DeleteMapping("/wish/{wishproductid}")
    public ResponseEntity deleteWishProduct(@PathVariable String wishproductid){
        productService.deleteWishProduct(Integer.valueOf(wishproductid));
        return new ResponseEntity(null,HttpStatus.OK);
    }
}
