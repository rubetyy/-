package com.ssafy.api.controller;

import com.ssafy.api.request.LiveRegisterPostReq;
import com.ssafy.api.request.LiveUpdate_titlePatchReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserUpdatePostReq;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.Live.LiveService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Live;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping()
public class LiveController {
	
	@Autowired
	LiveService liveService;
	
	@PostMapping("/live/live-start")
	@ApiOperation(value = "방송정보등록", notes = "방송정보를 통해 등록한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) LiveRegisterPostReq registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Live live = liveService.createLive(registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@Transactional
	@PatchMapping(value="/live/end/{userid}")
	@ApiOperation(value = "방송종료", notes = "회원수정을 진행.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="방송종료", required = true) @PathVariable String userid) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
//		System.out.println(userId);
		long a = liveService.endLive(userid);
		System.out.println(a);
		System.out.println(a);
		System.out.println(a);
		System.out.println(a);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@Transactional
	@PatchMapping(value="/live/title")
	@ApiOperation(value = "방송종료", notes = "회원수정을 진행.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="방송종료", required = true)LiveUpdate_titlePatchReq liveUpdate_titlePatchReq) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
//		System.out.println(userId);
		long a = liveService.updatetitleLive(liveUpdate_titlePatchReq);
		System.out.println(a);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
