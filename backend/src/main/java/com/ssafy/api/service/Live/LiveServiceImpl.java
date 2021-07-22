package com.ssafy.api.service.Live;

import com.ssafy.api.request.LiveRegisterPostReq;
import com.ssafy.api.request.LiveUpdate_titlePatchReq;
import com.ssafy.db.entity.Live;
import com.ssafy.db.repository.Live.LiveRepository;
import com.ssafy.db.repository.Live.LiveRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
 *	방송 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("liveService")
public class LiveServiceImpl implements LiveService {

	@Autowired
	LiveRepository liveRepository;

	@Autowired
	LiveRepositorySupport liveRepositorySupport;
	
	@Override
	public Live createLive(LiveRegisterPostReq liveRegisterInfo) {
		Live live = new Live();
		live.setUserid(liveRegisterInfo.getUserid());
		live.setLivetitle(liveRegisterInfo.getLivetitle());
		live.setLiveds(liveRegisterInfo.getLiveds());
		live.setLiveimg(liveRegisterInfo.getLiveimg());
		live.setLivewriter(liveRegisterInfo.getLivewriter());
		live.setLive_reg_date(Timestamp.valueOf(LocalDateTime.now()));

		return liveRepository.save(live);
	}

	@Override
	public Long endLive(String value) {
		Long a = liveRepositorySupport.endLive(value);
		return a;
	}

	@Override
	public Long updatetitleLive(LiveUpdate_titlePatchReq liveUpdate_titlePatchReq) {
		Long a = liveRepositorySupport.updatetitleLive(liveUpdate_titlePatchReq);
		return a;

	}


}
