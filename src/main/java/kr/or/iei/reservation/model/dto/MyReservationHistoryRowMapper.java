package kr.or.iei.reservation.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MyReservationHistoryRowMapper implements RowMapper<Reservation>{

	@Override
	public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reservation r = new Reservation();
		r.setReservationNo(rs.getInt("reservation_no"));
		r.setReservationStatus(rs.getInt("reservation_status"));
		r.setReservationType(rs.getInt("reservation_type"));
		r.setMemberName(rs.getString("member_name"));
		r.setChildName(rs.getString("child_name"));
		r.setHospitalNo(rs.getInt("hospital_no"));
		r.setHospitalName(rs.getString("hospital_name"));
		r.setResTimeDate(rs.getString("res_time_date"));
		r.setResTimeDay(rs.getString("res_time_day"));
		r.setResTimeTime(rs.getString("res_time_time"));
		r.setReviewNo(rs.getInt("review_no"));
		return r;
	}

}
