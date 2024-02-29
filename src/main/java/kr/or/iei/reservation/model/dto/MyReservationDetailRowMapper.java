package kr.or.iei.reservation.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MyReservationDetailRowMapper implements RowMapper<ReservationDetail> {

	@Override
	public ReservationDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReservationDetail rd = new ReservationDetail();
		rd.setRegDate(rs.getString("reg_date"));
		rd.setRegDay(rs.getString("reg_day"));
		rd.setRegTime(rs.getString("reg_time"));
		rd.setDoctorName(rs.getString("doctor_name"));
		rd.setSubjectName(rs.getString("subject_name"));
		rd.setSymptom(rs.getString("symptom"));
		rd.setReportCount(rs.getInt("report_count"));
		return rd;
	}
	
}
