package kr.or.iei.reservation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.hospital.model.dto.DoctorInfo;
import kr.or.iei.reservation.model.dao.ReservationDetailDao;
import kr.or.iei.reservation.model.dto.H_Reservation;
import kr.or.iei.reservation.model.dto.ReservationDetail;
import kr.or.iei.reservation.model.dto.ReservationDetailList;

@Service
public class ReservationDetailService {
	@Autowired
	private ReservationDetailDao reservationDetailDao;

	public String selectDoctor() {
		String doctorName = reservationDetailDao.selectDoctor();
		return doctorName;
	}

	public ReservationDetailList selectOneReservation(H_Reservation hr) {
		ReservationDetailList rdl = reservationDetailDao.selectOneReservation(hr);
		hr.setHospitalNo(rdl.getHospitalNo());
		System.out.println(rdl.toString());
		List doctorList = reservationDetailDao.getDoctorList(hr);
		List fileDataList = reservationDetailDao.getSymptomImg(hr);
		rdl.setFileDataList(fileDataList);
		rdl.setDoctorList(doctorList);
		return rdl;
	}

}
