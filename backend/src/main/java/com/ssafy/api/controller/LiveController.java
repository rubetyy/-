package com.ssafy.api.controller;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.LiveTitlePatchReq;
import com.ssafy.api.request.dto.Live.LiveMainDto;
import com.ssafy.api.response.dto.User.LivewithUser;
import com.ssafy.api.service.Live.LiveService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Image;
import com.ssafy.db.entity.Live;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "방송 API", tags = {"Live"})
@RestController
@RequestMapping()
public class LiveController {
	
	@Autowired
	LiveService liveService;

	@Transactional
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

		Live live = liveService.createLive(registerInfo);
		return new ResponseEntity<Live>(live,HttpStatus.OK);
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

		Tuple live = liveService.selectone(liveid);
		Live l = live.get(0,Live.class);
		User u = live.get(1, User.class);
		LivewithUser res = new LivewithUser();
		res.setLivepk(l.getLivepk());
		res.setProductpk(l.getProductpk());
		res.setLivetitle(l.getLivetitle());
		res.setLiveviewercount(l.getLiveviewercount());
		res.setUsernickname(u.getUsernickname());
		res.setUserid(u.getUserid());
		res.setUsercreatedat(u.getUserCreateAt());
		if(live == null) System.out.println("xxxxx");
		return new ResponseEntity<LivewithUser>(res, HttpStatus.OK);
	}

	//방송 종료
	@Transactional
	@DeleteMapping(value="/live/end/{liveid}")
	@ApiOperation(value = "방송종료", notes = "회원수정을 진행.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> endLive(
			@PathVariable String liveid) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		long a = liveService.endLive(liveid);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

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
			@RequestBody @ApiParam(value="방송제목수정", required = true) LiveTitlePatchReq liveTitlePatchReq) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		long a = liveService.updatetitleLive(liveTitlePatchReq);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
