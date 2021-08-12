package com.ssafy.api.controller;

import com.querydsl.core.Tuple;
import com.ssafy.api.response.dto.Live.LiveMainDto;
import com.ssafy.api.request.dto.Product.*;
import com.ssafy.api.response.dto.Live.LiveSearchDto;
import com.ssafy.api.service.FileHandler.FileHandlerService;
import com.ssafy.api.service.Live.LiveService;
import com.ssafy.api.service.Product.ProductService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Image;
import com.ssafy.db.entity.Live;
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
    UserService userService;
    //검색기능
    @PostMapping("/search")
    public ResponseEntity searchProduct(@RequestBody ProductSearchReq searchReq){
        String search = searchReq.getSearch();
        List<LiveSearchDto> ll = liveService.getSearchLives(search);
        List<Tuple> l = productService.getSearchProducts(search);
        List<Product> pl = new ArrayList<>();
        Map<Integer,Object> imap = new HashMap<>();
        Map<String,Object> res = new HashMap<>();
        for(Tuple t : l){
            Image i = t.get(1,Image.class);
            imap.put(i.getProduct().getId(),i);
        }
        res.put("liveList",ll);
        res.put("productList",imap);
        return new ResponseEntity(res,HttpStatus.OK);
    }

    //물건 판매 처리
    @Transactional
    @GetMapping("/sold/{productId}")
    public ResponseEntity soldProduct(@PathVariable String productId){
        Long a = productService.soldProduct(productId);
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

        Product product = productService.createProduct(productRegisterPostReq,images);

        return new ResponseEntity(product,HttpStatus.OK);
    }

    @Transactional//조회수 1증가 (update실행)
    @GetMapping("/{productId}")
    public ResponseEntity getProducts(@PathVariable String productId) {
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

    @GetMapping("/main")
    @ApiOperation(value = "상품 리스트 조회", notes = "메인페이지에서 상품정보 12개 조회.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity getMainInfo() {
        Map<String,Object> res = new HashMap<>();
        List<Tuple> l = productService.getMainProducts();
        int cnt = 0;
        List<Image> il = new ArrayList<>();
        for(Tuple t : l){
            Image image = t.get(0,Image.class);
            il.add(image);
        }
        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.

        List<LiveMainDto> liveman = liveService.selectMain();

        res.put("liveList",liveman);
        res.put("productList",il);

        return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
    }

    @PostMapping("/wish")
    public ResponseEntity wishProduct(@RequestBody ProductWishReq wishproduct) {
        productService.addWishProduct(wishproduct);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @DeleteMapping("/wish/{wishproductid}")
    public ResponseEntity deleteWishProduct(@PathVariable String wishproductid){
        productService.deleteWishProduct(Integer.valueOf(wishproductid));
        return new ResponseEntity(null,HttpStatus.OK);
    }
}
