package kr.or.iei.reservation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.reservation.model.dao.ReservationDao;

@Service
public class ReservationService {
	@Autowired
	private ReservationDao reservationDao;

	public List selectReservation() {
		List list = reservationDao.selectReservation();
		return list;
	}
}
