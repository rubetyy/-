package com.ssafy.api.service.Live;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.dto.Live.LiveTitlePatchReq;
import com.ssafy.api.request.dto.Live.LiveVIewerReq;
import com.ssafy.api.response.dto.Live.LiveCategoryDto;
import com.ssafy.api.response.dto.Live.LiveMainDto;
import com.ssafy.api.response.dto.Live.LiveSearchDto;
import com.ssafy.db.entity.Live;

import java.util.List;

/**
 *	방송 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface LiveService {
	Live createLive(Live liveRegisterPostReq);

	Long endLive(String value);

	Long updatetitleLive(LiveTitlePatchReq liveTitlePatchReq);

	Tuple selectone(int liveid);

	List<LiveMainDto> selectMain();

	List<LiveCategoryDto> getLiveByCategory(int categoryid);

	List<LiveSearchDto> getSearchLives(String search);
}