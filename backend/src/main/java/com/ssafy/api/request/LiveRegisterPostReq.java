package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("LiveRegisterPostRequest")
public class LiveRegisterPostReq {
	@ApiModelProperty(name="방송 ID", example="ssafy_web")
	String id;
	@ApiModelProperty(name="방송 userid", example="your_password")
	@Column(name="userid")
	String userid;
	@ApiModelProperty(name="방송 title")
	String livetitle;
	@ApiModelProperty(name="방송 description")
	String liveds;
	@ApiModelProperty(name="방송 img경로")
	String liveimg;
	@ApiModelProperty(name="방송 livewriter?")
	String livewriter;
	@ApiModelProperty(name="방송 live on/off")
	int liveon;
}
