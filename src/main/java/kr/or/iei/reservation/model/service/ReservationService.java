package kr.or.iei.reservation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.reservation.model.dao.ReservationDao;
import kr.or.iei.reservation.model.dto.ReservationListData;

@Service
public class ReservationService {
	@Autowired
	private ReservationDao reservationDao;

	public ReservationListData selectReservation(int reqPage) {
		//게시물 수 지정
		int numPerPage = 10;
		//시작 페이지 / 끝페이지
		int endPage = reqPage * numPerPage;
		int startPage = endPage - numPerPage + 1;
		List list = reservationDao.selectReservation(startPage,endPage);
		
		//페이지 네비게이션 제작
		//게시물 수 조회
		int totalCount = reservationDao.selectAllReservationCount();
		
		//전체 페이지 계산
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		
		//네비게이션 사이즈
		int pageNaviSize = 5;
		
		//페이지 네비게이션 시작 번호
		int pageNo = ((reqPage - 1)/pageNaviSize)*pageNaviSize + 1;
		//html 코드 작성
		String pageNavi = "<ul class='pagination'>";
		//이전 버튼생성 코드
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/hospital/myHospitalReservation?reqPage="+(pageNo -1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//숫자 생성
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/hospital/myHospitalReservation?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/hospital/myHospitalReservation?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			//최종 페이지 출력 시 for문 나가야함
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음 버튼 생성(출력번호+1 한 숫자가 최종 페이지 보다 같거나 작으면 다음버튼 생성)
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/hospital/myHospitalReservation?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		ReservationListData rld = new ReservationListData(list, pageNavi);
		return rld;
	}
	@Transactional
	public int updateReservation(int selectValue, int reservationNo) {
		int result = reservationDao.updateReservation(selectValue,reservationNo);
		return result;
	}
}
