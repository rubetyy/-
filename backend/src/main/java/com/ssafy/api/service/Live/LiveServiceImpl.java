package com.ssafy.api.service.Live;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.dto.Live.LiveTitlePatchReq;
import com.ssafy.api.request.dto.Live.LiveVIewerReq;
import com.ssafy.api.response.dto.Live.LiveCategoryDto;
import com.ssafy.api.response.dto.Live.LiveMainDto;
import com.ssafy.api.response.dto.Live.LiveSearchDto;
import com.ssafy.db.entity.Live;
import com.ssafy.db.repository.Live.LiveRepository;
import com.ssafy.db.repository.Live.LiveRepositorySupport;
import com.ssafy.db.repository.Product.ProductRepository;
import com.ssafy.db.repository.Product.ProductRepositorySupport;
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

	@Autowired
	ProductRepositorySupport productRepositorySupport;
	
	@Override
	public Live createLive(Live liveRegisterInfo) {
		Live live = new Live();
		live.setProductpk(liveRegisterInfo.getProductpk());
		live.setLivetitle(liveRegisterInfo.getLivetitle());
		live.setUserid(liveRegisterInfo.getUserid());
		live.setIslive(1);//라이브중임을 db에 1로 저장
		liveRepository.save(live);

		live = liveRepositorySupport.findMaxIdx();
		//라이브 시작시 Product테이블에 업데이트함
		productRepositorySupport.setLivepk(live.getLivepk(),live.getProductpk());

		return live;
	}

	@Override
	public Long endLive(String value) {
		productRepositorySupport.endLive(Integer.valueOf(value));
		return liveRepositorySupport.endLive(Integer.valueOf(value));
	}

	@Override
	public Long updatetitleLive(LiveTitlePatchReq liveTitlePatchReq) {
		Long a = liveRepositorySupport.updatetitleLive(liveTitlePatchReq);
		return a;
	}

	@Override
	public Tuple selectone(int liveid) {
		liveRepositorySupport.updateViewerCount(liveid);
		return liveRepositorySupport.findByLiveId(liveid);
	}

	@Override
	public List<LiveMainDto> selectMain() {
		return liveRepositorySupport.selectMain();
	}

	@Override
	public List<LiveCategoryDto> getLiveByCategory(int categoryid) {
		List<LiveCategoryDto> t = liveRepositorySupport.getLiveByCategoryId(categoryid);
		return t;
	}

	@Override
	public List<LiveSearchDto> getSearchLives(String search) {
		return liveRepositorySupport.getSearchLives(search);
	}

	@Override
	public int updateViewerCount(LiveVIewerReq liveVIewerReq) {
		return liveRepositorySupport.updateViewerCount(liveVIewerReq);
	}

}
