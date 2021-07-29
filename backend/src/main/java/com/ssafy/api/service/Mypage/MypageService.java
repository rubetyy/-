package com.ssafy.api.service.Mypage;

import com.ssafy.api.request.dto.User.UserRegisterPostReq;
import com.ssafy.db.entity.User;

import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface MypageService {
	Map<String,Object> mypageinfo(String userid);
}
