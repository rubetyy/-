package com.ssafy.api.controller;

import com.ssafy.api.service.Mypage.MypageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "마이페이지 API",tags = {"Mypage"})
@RestController
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    MypageServiceImpl mypageService;

    @GetMapping("/{userId}")
    @ApiOperation(value = "마이페이지", notes = "마이페이지에서 요구하는 데이터 반환.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity getUserInfo(@PathVariable String userId) {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */
		/*
		토큰이 존재하는 경우 {
			Authorization : Bearer + 토큰 값
			}
			으로 헤더에 전송한다. in postman
		 */
        Map<String,Object> res = mypageService.mypageinfo(userId);
        return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
    }
}
