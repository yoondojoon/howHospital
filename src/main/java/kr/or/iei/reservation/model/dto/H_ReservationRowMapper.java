package kr.or.iei.reservation.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class H_ReservationRowMapper implements RowMapper<H_Reservation>{

	@Override
	public H_Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
		H_Reservation hr = new H_Reservation();
		hr.setDoctorName(rs.getString("doctor_name"));
		hr.setMemberName(rs.getString("member_name"));
		hr.setRegReservation(rs.getString("reg_reservation"));
		hr.setReservationStatus(rs.getInt("reservation_status"));
		hr.setReservationTime(rs.getString("reservation_time"));
		hr.setReservationType(rs.getInt("reservation_type"));
		hr.setReservationNo(rs.getInt("reservation_no"));
		
		return hr;
	}
	
}
