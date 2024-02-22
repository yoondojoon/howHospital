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
	public List selectReservation(int startPage, int endPage) {
		String query = "select * from\r\n" + 
				"            (select rownum as rnum, res.* from\r\n" + 
				"            (select reservation_no,reservation_status,reg_reservation,RESERVATION_TIME,reservation_type,(select member_name from member_tbl where member_no=r.member_no) as member_name,\r\n" + 
				"            (select DOCTOR_NAME from DOCTOR_TBL where doctor_no = (select doctor_no from reservation_detail_tbl where reservation_no=r.reservation_no)) as doctor_name\r\n" + 
				"FROM \r\n" + 
				"reservation_tbl r\r\n" + 
				"ORDER BY 1 DESC)RES) WHERE Rnum BETWEEN ? AND ?";
		Object[] params = {startPage, endPage};
		List list = jdbc.query(query, h_ReservationRowMapper, params);
		return list;
	}

	public int updateReservation(int selectValue, int reservationNo) {
		String query = "update reservation_tbl set reservation_status=? where reservation_no=?";
		Object[] params = {selectValue, reservationNo};
		int result = jdbc.update(query, params);
		return result;
	}

	public int selectAllReservationCount() {
		String query = "select count(*) from reservation_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
	
}
