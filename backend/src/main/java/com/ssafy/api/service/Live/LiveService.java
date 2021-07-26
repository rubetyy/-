package com.ssafy.api.service.Live;

import com.ssafy.api.request.LiveUpdate_titlePatchReq;
import com.ssafy.db.entity.Live;

import java.util.List;
import java.util.Optional;

/**
 *	방송 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface LiveService {
	Live createLive(Live liveRegisterPostReq);
//	Long endLive(String value);
	Long updatetitleLive(LiveUpdate_titlePatchReq liveUpdate_titlePatchReq);
	Live selectone(String liveid);
	List<Live> selectall();
}
