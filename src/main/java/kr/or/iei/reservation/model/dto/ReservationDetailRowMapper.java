package kr.or.iei.reservation.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationDetailRowMapper implements RowMapper<ReservationDetail>{

	@Override
	public ReservationDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReservationDetail rd = new ReservationDetail();
		rd.setDoctorNo(rs.getInt("doctor_no"));
		rd.setReservationDetailNo(rs.getInt("reservation_detail_no"));
		rd.setReservationNo(rs.getInt("reservation_no"));
		rd.setSubjectNo(rs.getInt("subject_no"));
		rd.setSymptom(rs.getString("symptom"));
		rd.setMemberName(rs.getString("member_name"));
		return rd;
	}
	
}
