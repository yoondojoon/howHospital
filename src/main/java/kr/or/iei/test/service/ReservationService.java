package kr.or.iei.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.test.dao.ReservationDao;

@Service
public class ReservationService {
	@Autowired
	private ReservationDao reservationDao;

	public List selectReservation() {
		List list = reservationDao.selectReservation();
		return list;
	}
}
