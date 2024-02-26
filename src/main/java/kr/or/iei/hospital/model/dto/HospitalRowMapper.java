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
		h.setHospitalNo(rs.getInt("hospital_no"));
		h.setMemberNo(rs.getInt("member_no"));
		h.setHospitalName(rs.getString("hospital_name"));
		h.setHospitalIntro(rs.getString("hospital_intro"));
		h.setHospitalPostcode(rs.getString("hospital_postcode"));
		h.setHospitalAddrMain(rs.getString("hospital_addr_main"));
		h.setHospitalAddrSub(rs.getString("hospital_addr_sub"));
		h.setLat(rs.getString("lat"));
		h.setLng(rs.getString("lng"));
		h.setHospitalTel(rs.getString("hospital_tel"));
		h.setCostOne(rs.getString("cost_one"));
		h.setCostTwo(rs.getString("cost_two"));
		h.setHospitalPicture(rs.getString("hospital_picture"));
		return h;
	}

}
