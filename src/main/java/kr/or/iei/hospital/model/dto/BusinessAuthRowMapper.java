package kr.or.iei.hospital.model.dto;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BusinessAuthRowMapper implements RowMapper<BusinessAuth>{

	@Override
	public BusinessAuth mapRow(ResultSet rs, int rowNum) throws SQLException {
		BusinessAuth ba = new BusinessAuth();
		ba.setMemberNo(rs.getInt("member_no"));
		ba.setRepresentativeNo(rs.getInt("representative_no"));
		ba.setBusinessCertificate(rs.getString("business_certificate"));
		ba.setDoctorCertificate(rs.getNString("doctor_certificate"));
		return ba;
	}

	
}
