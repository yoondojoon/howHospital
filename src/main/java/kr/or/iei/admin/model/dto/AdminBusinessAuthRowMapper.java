package kr.or.iei.admin.model.dto;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AdminBusinessAuthRowMapper implements RowMapper<AdminBusinessAuth>{
	
	public AdminBusinessAuth mapRow(ResultSet rs, int rowNum) throws SQLException{
		AdminBusinessAuth ab = new AdminBusinessAuth();
		ab.setMemberName(rs.getString("member_name"));
		ab.setBusinessAuthNo(rs.getInt("businessAuth_no"));
		ab.setMemberEmail(rs.getString("member_email"));
		ab.setMemberPhone(rs.getString("member_phone"));
		ab.setRegDate(rs.getString("reg_date"));
		ab.setRepresentativeNo(rs.getString("representative_no"));
		return ab;
	}
}
