package com.ssafy.api.service.Live;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.LiveTitlePatchReq;
import com.ssafy.api.request.dto.Live.LiveMainDto;
import com.ssafy.db.entity.Live;
import com.ssafy.db.repository.Live.LiveRepository;
import com.ssafy.db.repository.Live.LiveRepositorySupport;
import com.ssafy.db.repository.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *	방송 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("liveService")
public class LiveServiceImpl implements LiveService {

	@Autowired
	LiveRepository liveRepository;

	@Autowired
	LiveRepositorySupport liveRepositorySupport;

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Live createLive(Live liveRegisterInfo) {
		Live live = new Live();
		live.setProductpk(liveRegisterInfo.getProductpk());
		live.setLivetitle(liveRegisterInfo.getLivetitle());
		live.setUserid(liveRegisterInfo.getUserid());
		liveRepository.save(live);

		live = liveRepositorySupport.findMaxIdx();

		return live;
	}

	@Override
	public Long updatetitleLive(LiveTitlePatchReq liveTitlePatchReq) {
		Long a = liveRepositorySupport.updatetitleLive(liveTitlePatchReq);
		return a;
	}

	@Override
	public Tuple selectone(String liveid) {
		return liveRepositorySupport.findByLiveId(Long.valueOf(liveid));
	}

	@Override
	public List<LiveMainDto> selectMain() {
		return liveRepositorySupport.selectMain();
	}

}
