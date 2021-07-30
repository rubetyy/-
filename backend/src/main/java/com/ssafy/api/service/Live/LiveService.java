package com.ssafy.api.service.Live;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.LiveTitlePatchReq;
import com.ssafy.db.entity.Live;

import java.util.List;

/**
 *	방송 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface LiveService {
	Live createLive(Live liveRegisterPostReq);
//	Long endLive(String value);
	Long updatetitleLive(LiveTitlePatchReq liveTitlePatchReq);
	Tuple selectone(String liveid);
	List<Live> selectall();
}
