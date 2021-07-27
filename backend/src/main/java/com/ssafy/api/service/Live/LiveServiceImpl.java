package com.ssafy.api.service.Live;

import com.ssafy.api.request.LiveUpdate_titlePatchReq;
import com.ssafy.db.entity.Live;
import com.ssafy.db.repository.Live.LiveRepository;
import com.ssafy.db.repository.Live.LiveRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	public Live createLive(Live liveRegisterInfo) {
		Live live = new Live();
		live.setLivepk(liveRegisterInfo.getLivepk());
		live.setProductpk(liveRegisterInfo.getProductpk());
		live.setLivetitle(liveRegisterInfo.getLivetitle());
		live.setLiveviewer_count(liveRegisterInfo.getLiveviewer_count());

		return liveRepository.save(live);
	}

	@Override
	public Long updatetitleLive(LiveUpdate_titlePatchReq liveUpdate_titlePatchReq) {
		Long a = liveRepositorySupport.updatetitleLive(liveUpdate_titlePatchReq);
		return a;

	}

	@Override
	public Live selectone(String liveid) {
		return liveRepositorySupport.findByLiveId(Integer.valueOf(liveid));
	}

	@Override
	public List<Live> selectall() {
		return liveRepository.findAll();
	}

}
