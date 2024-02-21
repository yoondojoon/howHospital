package kr.or.iei.reservation.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.reservation.model.dto.H_ReservationRowMapper;
import kr.or.iei.reservation.model.dto.ReservationRowMapper;

@Repository
public class ReservationDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private H_ReservationRowMapper h_ReservationRowMapper;
	public List selectReservation() {
		String query="SELECT \r\n" + 
				"    reservation_no,\r\n" + 
				"    reservation_status,\r\n" + 
				"    reg_reservation,\r\n" + 
				"    RESERVATION_TIME,\r\n" + 
				"    (select member_name from member_tbl where member_no=r.member_no) as member_name,\r\n" + 
				"    (select DOCTOR_NAME from DOCTOR_TBL where doctor_no = (select doctor_no from reservation_detail_tbl where reservation_no=r.reservation_no)) as doctor_name\r\n" + 
				"FROM \r\n" + 
				"    reservation_tbl r";
		/*
		String query = "SELECT \r\n" + 
				"    (SELECT doctor_name FROM doctor_tbl WHERE doctor_tbl.doctor_no = reservation_detail_tbl.doctor_no) AS doctor_name,\r\n" + 
				"    reservation_status,\r\n" + 
				"    reg_reservation,\r\n" + 
				"    member_name\r\n" + 
				"FROM \r\n" + 
				"    reservation_tbl\r\n" + 
				"JOIN \r\n" + 
				"    member_tbl ON reservation_tbl.member_no = member_tbl.member_no\r\n" + 
				"JOIN \r\n" + 
				"    reservation_detail_tbl ON reservation_tbl.reservation_no = reservation_detail_tbl.reservation_no";
				*/
		List list = jdbc.query(query, h_ReservationRowMapper);
		return list;
	}

	public int updateReservation(int selectValue, int reservationNo) {
		String query = "update reservation_tbl set reservation_status=? where reservation_no=?";
		Object[] params = {selectValue, reservationNo};
		int result = jdbc.update(query, params);
		return result;
	}
	
}
