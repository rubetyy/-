package com.ssafy.api.service.Mypage;

import com.ssafy.db.repository.Mypage.MypageRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("mypageService")
public class MypageServiceImpl implements MypageService {
	@Autowired
	MypageRepositorySupport mypageRepositorySupport;
	@Override
	public Map<String, Object> mypageinfo(String userid) {
		return mypageRepositorySupport.mypageinfo(userid);
	}

}
