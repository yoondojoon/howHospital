package kr.or.iei.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.community.model.dao.CommunityDao;
import kr.or.iei.community.model.dto.CommunityListData;

@Service
public class CommunityService {
	@Autowired
	private CommunityDao communityDao;

	public CommunityListData selectCommunityList(String keyword, int reqPage) {
		int numPerPage = 6;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		List list = communityDao.selectCommunityList(keyword, start, end);
		
		int totalCount = communityDao.selectTotalCount();
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		
		int pageNaviSize = 5;
		
		return null;
	}
}
