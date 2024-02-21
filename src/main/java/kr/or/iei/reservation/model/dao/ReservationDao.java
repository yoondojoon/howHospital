package kr.or.iei.reservation.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.iei.reservation.model.dto.ReservationRowMapper;

@Repository
public class ReservationDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ReservationRowMapper reservationRowMapper;
	public List selectReservation() {
		String query = "select * from RESERVATION_TBL where reservation_no=1 and hospital_no=22 and member_no=36 and reservation_status=1 and reg_reservation='2024-02-20 19:26:14' and reservation_type=1";
		List list = jdbc.query(query, reservationRowMapper);
		return list;
	}
	
}
