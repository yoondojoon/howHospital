package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
@Component
public class DoctorInfoRowMapper implements RowMapper<DoctorInfo>{

	@Override
	public DoctorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		DoctorInfo di = new DoctorInfo();
		di.setDoctorName(rs.getString("doctor_name"));
		di.setDoctorNo(rs.getInt("doctor_no"));
		di.setReservationDetailNo(rs.getInt("reservation_detail_no"));
		return di;
	}

}
