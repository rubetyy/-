package com.ssafy.api.controller;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.dto.Live.LiveTitlePatchReq;
import com.ssafy.api.request.dto.Live.LiveDetailReq;
import com.ssafy.api.request.dto.Live.LiveVIewerReq;
import com.ssafy.api.response.dto.Live.LiveWishRes;
import com.ssafy.api.response.dto.Product.ProductWishRes;
import com.ssafy.api.response.dto.User.LivewithUser;
import com.ssafy.api.service.Live.LiveService;
import com.ssafy.api.service.Product.ProductServiceImpl;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Live;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.Wish;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "방송 API", tags = {"Live"})
@RestController
@RequestMapping("/live")
public class LiveController {
	
	@Autowired
	LiveService liveService;

	@Autowired
	ProductServiceImpl productService;

	@Transactional
	@PostMapping("/live-start")
	public ResponseEntity create(
			@RequestBody @ApiParam(value="방송생성정보반환", required = true) Live registerInfo) {

		Live live = liveService.createLive(registerInfo);
		return new ResponseEntity<Live>(live,HttpStatus.OK);
	}

	@Transactional
	@PostMapping()
	@ApiOperation(value = "해당방송정보반환", notes = "방송정보를 반환한다.")
	public ResponseEntity selectOne(@RequestBody  LiveDetailReq liveDetailReq) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Tuple live = liveService.selectone(liveDetailReq.getLiveid());
		Live l = live.get(0,Live.class);
		User u = live.get(1, User.class);
		LiveWishRes lres = new LiveWishRes();
		LivewithUser r = new LivewithUser(l,u);
		if(liveDetailReq.getUserid() != null){
			Wish w = productService.findWish(l.getProductpk(),liveDetailReq.getUserid());
			if(w == null) {
				lres.setFlag(false);
				r.setWish(lres);
			}
			else {
				lres.setWishproductpk(w.getWishproductpk());
				lres.setFlag(true);
				r.setWish(lres);
			}
		}
		return new ResponseEntity(r, HttpStatus.OK);
	}

	@Transactional
	@PatchMapping(value="/title")
	@ApiOperation(value = "방송제목수정", notes = "방송제목수정을 진행.")
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="방송제목수정", required = true) LiveTitlePatchReq liveTitlePatchReq) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		long a = liveService.updatetitleLive(liveTitlePatchReq);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

}
