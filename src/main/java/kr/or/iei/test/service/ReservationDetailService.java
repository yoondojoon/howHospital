package kr.or.iei.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.test.dao.ReservationDetailDao;
import kr.or.iei.test.dto.ReservationDetail;

@Service
public class ReservationDetailService {
	@Autowired
	private ReservationDetailDao reservationDetailDao;

	public String selectDoctor() {
		String doctorName = reservationDetailDao.selectDoctor();
		return doctorName;
	}
}
