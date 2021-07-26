package com.ssafy.api.controller;

import com.ssafy.api.request.LiveUpdate_titlePatchReq;
import com.ssafy.api.service.Live.LiveService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Live;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "방송 API", tags = {"Live"})
@RestController
@RequestMapping()
public class LiveController {
	
	@Autowired
	LiveService liveService;

	@PostMapping("/live/live-start")
	@ApiOperation(value = "방송생성 후 정보반환", notes = "방송 생성정보를 반환한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity create(
			@RequestBody @ApiParam(value="방송생성정보반환", required = true) Live registerInfo) {

		//
		Live live = liveService.createLive(registerInfo);

		return new ResponseEntity<Live>(live,HttpStatus.OK);
	}


	@GetMapping("/live/main")
	@ApiOperation(value = "방송목록조회", notes = "main페이지 진입시 방송목록을 12개 반환한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity selectAll() {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		List<Live> l = liveService.selectall();
		return new ResponseEntity<List<Live>>(l, HttpStatus.OK);
	}

	@GetMapping("/live/{liveid}")
	@ApiOperation(value = "해당방송정보반환", notes = "방송정보를 반환한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity selectOne(
			@RequestBody @ApiParam(value="방송pk 정보", required = true) @PathVariable String liveid) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Live live = liveService.selectone(liveid);
		System.out.println("xxx");
		return new ResponseEntity<Live>(live, HttpStatus.OK);
	}

//	@Transactional
//	@PatchMapping(value="/live/end/{userid}")
//	@ApiOperation(value = "방송종료", notes = "회원수정을 진행.")
//	@ApiResponses({
//			@ApiResponse(code = 200, message = "성공"),
//			@ApiResponse(code = 401, message = "인증 실패"),
//			@ApiResponse(code = 404, message = "사용자 없음"),
//			@ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<? extends BaseResponseBody> register(
//			@RequestBody @ApiParam(value="방송종료", required = true) @PathVariable String userid) {
//
//		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
////		System.out.println(userId);
//		long a = liveService.endLive(userid);
//		System.out.println(a);
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//	}

	@Transactional
	@PatchMapping(value="/live/title")
	@ApiOperation(value = "방송제목수정", notes = "방송제목수정을 진행.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="방송제목수정", required = true)LiveUpdate_titlePatchReq liveUpdate_titlePatchReq) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
//		System.out.println(userId);
		long a = liveService.updatetitleLive(liveUpdate_titlePatchReq);
		System.out.println(a);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}



}
