package kr.or.iei.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.admin.model.dao.AdminDao;
import kr.or.iei.admin.model.dto.NoticeListData;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	
	public NoticeListData selectAllNotice(int reqPage) {
		
		//페이지당 게시슬 갯수 10개? 좀 더 많이 보여줄까.....
		int numPerPage = 10;
		
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		List list = adminDao.selectAllNotice(start,end);
		int totalCount = adminDao.selectAllBoardCount();
		
		//totalPage 생성
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;			
		}
		
		//네비게이션 사이즈
		int pageNaviSize = 10;
		
		//페이지 네비게이션 시작번호
		//reqPage 1~10 이전버튼 1 2 3 4 5 6 7 8 9 10 다음버튼
		//reqPage 11~20 이전버튼 11 12 13 14 15 16 17 18 19 20 다음버튼
		
		//페이지의 네비게이션 1~10까지는 1페이지의 네비게이션 11~20까지는 2페이지의 네비게이션
		int pageNo =((reqPage -1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		if(pageNo !=1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/noticeList?reqPage="+ (pageNo-1) +"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지 숫자 생성
		for(int i = 0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/admin/noticeList?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {				
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/admin/noticeList?reqPage="+ (pageNo) +"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			//페이지를 만들다가 최종페이지를 출력했으면 더이상 반복하지 않고 종료
			if(pageNo > totalPage) {
				break;
			}
		}
		
		//다음버튼(출력한 번호+1 한 숫자가 최종페이지보다 같거나 작으면(최종페이지를 아직 출력하지 않았으면)
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/noticeList?reqPage="+ (pageNo) +"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		//list, pageNavi 두개를 모두 리턴 -> java의 메소드는 리턴타입이 1개
		//->두개를 저장하는 객체를 리턴
		
		NoticeListData nld = new NoticeListData(list, pageNavi);
		return nld;
	}

}
