package kr.or.iei.reservation.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationDoctorListRowMapper implements RowMapper<ReservationDoctorList>{

	@Override
	public ReservationDoctorList mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReservationDoctorList rdl = new ReservationDoctorList();
		rdl.setDoctorName(rs.getString("doctor_name"));
		rdl.setDoctorNo(rs.getInt("doctor_no"));
		//rdl.setHospitalNo(rs.getInt("hospital_no"));
		return rdl;
	}

}
