package kr.or.iei.reservation.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.reservation.model.dto.H_Reservation;
import kr.or.iei.reservation.model.dto.ReservationDetail;
import kr.or.iei.reservation.model.dto.ReservationDetailList;
import kr.or.iei.reservation.model.dto.ReservationDetailListRowMapper;
import kr.or.iei.reservation.model.dto.ReservationDetailRowMapper;
import kr.or.iei.reservation.model.dto.ReservationFileDataRowMapper;

@Repository
public class ReservationDetailDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ReservationDetailRowMapper reservationDetailRowMapper;
	@Autowired
	private ReservationDetailListRowMapper reservationDetailListRowMapper;
	@Autowired
	private ReservationFileDataRowMapper reservationFileDataRowMapper;
	
	public String selectDoctor() {
		String query = "select doctor_name from reservation_detail_tbl join doctor_tbl using (doctor_no)";
		String doctorName = jdbc.queryForObject(query, String.class);
		return doctorName;
	}
	public ReservationDetailList selectOneReservation(H_Reservation hr) {
		String query = "SELECT DISTINCT\r\n" + 
				"    R.RESERVATION_NO, R.RESERVATION_STATUS, R.RESERVATION_TYPE, R.RESERVATION_TIME,\r\n" + 
				"    RD.SYMPTOM, RF.FILENAME, RF.FILEPATH, M.MEMBER_NAME,\r\n" + 
				"    (SELECT COUNT(*) FROM PRESCRIPTION_TBL WHERE RESERVATION_NO = R.RESERVATION_NO) AS PRESCRIPTION_STATUS\r\n" + 
				"FROM \r\n" + 
				"    RESERVATION_TBL R\r\n" + 
				"LEFT JOIN \r\n" + 
				"    RESERVATION_FILE RF ON R.RESERVATION_NO = RF.RESERVATION_NO\r\n" + 
				"LEFT JOIN \r\n" + 
				"    MEMBER_TBL M ON R.MEMBER_NO = M.MEMBER_NO\r\n" + 
				"LEFT JOIN\r\n" + 
				"    RESERVATION_DETAIL_TBL RD ON R.RESERVATION_NO = RD.RESERVATION_NO\r\n" + 
				"WHERE \r\n" + 
				"    R.RESERVATION_NO = ?";
		Object[] params = {hr.getReservationNo()};
		List list = jdbc.query(query, reservationDetailListRowMapper,params);
		if(list.isEmpty()) {
			return null;
		}
		return (ReservationDetailList)list.get(0);
	}
	public List getSymptomImg(H_Reservation hr) {
		String query = "SELECT RD.RESERVATION_NO AS RESERVATION_NO, RF.FILEPATH AS FILEPATH\r\n" + 
				"FROM RESERVATION_DETAIL_TBL RD\r\n" + 
				"JOIN RESERVATION_FILE RF ON RD.RESERVATION_NO = RF.RESERVATION_NO\r\n" + 
				"WHERE RD.RESERVATION_NO = ?";
		Object[] params = {hr.getReservationNo()};
		List list = jdbc.query(query, reservationFileDataRowMapper, params);
		return list;
	}
}
