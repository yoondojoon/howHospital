package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class HospitalSearchRowMapper implements RowMapper<Hospital> {

	@Override
	public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hospital h = new Hospital();
		h.setHospitalNo(rs.getInt("hospital_no"));
		h.setHospitalName(rs.getString("hospital_name"));
		h.setHospitalAddress(rs.getString("hospital_address"));
		h.setLat(rs.getNString("lat"));
		h.setLng(rs.getNString("lng"));
		h.setOpenStatus(rs.getString("open_status"));
		return h;
	}
	
}
