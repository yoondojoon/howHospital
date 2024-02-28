package kr.or.iei.community.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.community.model.dao.CommunityDao;
import kr.or.iei.community.model.dto.Community;
import kr.or.iei.community.model.dto.CommunityFile;
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
		//1,6,11,16
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination'>";
		if(pageNo != 1) {
			pageNavi += "<li><a class='page_item btn_prev' href='/community/communityMain?reqPage="+(pageNo-1)+"'></a></li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li><a class='page_item active' href='/community/communityMain?reqPage="+pageNo+"'>"+pageNo+"</a></li>";
			}else {
				pageNavi += "<li><a class='page_item' href='/community/communityMain?reqPage="+pageNo+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li><a class='page_item btn_next' href='/community/communityMain?reqPage="+pageNo+"'></a></li>"; 
		}
		pageNavi += "</ul>";
		CommunityListData cld = new CommunityListData();
		cld.setList(list);
		cld.setPageNavi(pageNavi);
		return cld;
	}

	@Transactional
	public int insertCommunityBoard(Community c, List<CommunityFile> fileList) {
		int result = communityDao.insertCommunityBoard(c);
		if(result > 0) {
			int boardNo = communityDao.selectCommunityBoardNo();
			for(CommunityFile communityFile : fileList) {
				communityFile.setBoardNo(boardNo);
				result += communityDao.insertCommunityFile(communityFile);
			}
		}
		return result;
	}

	public Community selectOneBoard(int boardNo) {
		int result = communityDao.updateReadCount(boardNo);
		if(result > 0) {
			Community c = communityDao.selectOneCommunityBoard(boardNo);
			List fileList = communityDao.selectCommunityFile(boardNo);
			c.setFileList(fileList);
			return c;
		}else {
			return null;
		}
	}

	@Transactional
	public List deleteCommunityBoard(int boardNo) {
		List fileList = communityDao.selectCommunityFile(boardNo);
		int result = communityDao.deleteCommunityBoard(boardNo);
		if(result > 0) {
			return fileList;			
		}else {
			return null;
		}
	}

	public Community getOneBoard(int boardNo) {
		Community c = communityDao.selectOneCommunityBoard(boardNo);
		List fileList = communityDao.selectCommunityFile(boardNo);
		c.setFileList(fileList);
		return c;
	}

	public List updateCommunityBoard(Community c, List<CommunityFile> fileList, int[] delFileNo) {
		List delFileList = new ArrayList<CommunityFile>();
		int result = communityDao.updateCommunityBoard(c);
		if(result > 0) {
			if(delFileNo != null) {
				for(int fileNo : delFileNo) {
					CommunityFile communityFile = communityDao.selectOneBoardFile(fileNo);
					delFileList.add(communityFile);
					result += communityDao.deleteCommunityBoardFile(fileNo);
				}
			}
			for(CommunityFile communityFile : fileList) {
				result += communityDao.insertCommunityFile(communityFile);				
			}
		}
		int updateTotal = (delFileNo==null) ? fileList.size()+1 : fileList.size()+1+delFileNo.length;
		if(updateTotal == result) {
			return delFileList;
		}else {
			return null;
		}
	}
}








