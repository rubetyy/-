package com.ssafy.api.service.Live;

import com.ssafy.api.request.LiveRegisterPostReq;
import com.ssafy.api.request.LiveUpdate_titlePatchReq;
import com.ssafy.db.entity.Live;

/**
 *	방송 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface LiveService {
	Live createLive(LiveRegisterPostReq liveRegisterPostReq);
	Long endLive(String value);
	Long updatetitleLive(LiveUpdate_titlePatchReq liveUpdate_titlePatchReq);

}
