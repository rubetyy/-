package com.ssafy.api.response.dto.Mypage;

import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("MypageProductResponse")
public class MypagePRes {
	@ApiModelProperty(name="Product PK")
	Long productId;
	String title;
}
