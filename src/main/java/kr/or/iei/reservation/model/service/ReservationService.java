package kr.or.iei.reservation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.reservation.model.dao.ReservationDao;

@Service
public class ReservationService {
	@Autowired
	private ReservationDao reservationDao;

	public List selectReservation(int reqPage) {
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
		
		return list;
	}
	@Transactional
	public int updateReservation(int selectValue, int reservationNo) {
		int result = reservationDao.updateReservation(selectValue,reservationNo);
		return result;
	}
}
