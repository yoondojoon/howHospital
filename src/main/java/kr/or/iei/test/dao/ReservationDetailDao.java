package kr.or.iei.test.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.test.dto.ReservationDetail;
import kr.or.iei.test.dto.ReservationDetailRowMapper;

@Repository
public class ReservationDetailDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ReservationDetailRowMapper reservationDetailRowMapper;
	public String selectDoctor() {
		String query = "select doctor_name from reservation_detail_tbl join doctor_tbl using (doctor_no)";
		String doctorName = jdbc.queryForObject(query, String.class);
		return doctorName;
	}
}
