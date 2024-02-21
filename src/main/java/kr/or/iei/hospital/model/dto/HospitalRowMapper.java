package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class HospitalRowMapper implements RowMapper<Hospital> {

	@Override
	public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hospital h = new Hospital();
		h.setCostOne(rs.getString("cost_one"));
		h.setCostTwo(rs.getString("cost_two"));
		h.setHospitalAddress(rs.getString("hospital_address"));
		h.setHospitalIntro(rs.getString("hospital_intro"));
		h.setHospitalName(rs.getString("hospital_name"));
		h.setHospitalNo(rs.getInt("hospital_no"));
		h.setHospitalTel(rs.getString("hospital_tel"));
		h.setLat(rs.getString("lat"));
		h.setLng(rs.getString("lng"));
		h.setMemberNo(rs.getInt("member_no"));
		return h;
	}

}
