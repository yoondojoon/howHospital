package kr.or.iei.reservation.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.reservation.model.dto.H_Reservation;
import kr.or.iei.reservation.model.dto.ReservationDetail;
import kr.or.iei.reservation.model.dto.ReservationDetailRowMapper;

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
	public ReservationDetail selectOneReservation(H_Reservation hr) {
		String query = "select rd.*,\r\n" + 
				"    (select (select member_name from member_tbl where member_no=r.member_no) from reservation_tbl r where r.reservation_no = rd.reservation_no) member_name\r\n" + 
				"    from reservation_detail_tbl rd";
		List list = jdbc.query(query, reservationDetailRowMapper);
		if(list.isEmpty()) {
			return null;
		}
		return (ReservationDetail)list.get(0);
	}
}
