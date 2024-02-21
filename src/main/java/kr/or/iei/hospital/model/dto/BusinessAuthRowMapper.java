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
		ba.setBusinessAuthNo(rs.getInt("businessauth_no"));
		ba.setMemberNo(rs.getInt("member_no"));
		ba.setRepresentativeNo(rs.getInt("representative_no"));
		ba.setRegDate(rs.getString("reg_date"));
		return ba;
	}

	
}
