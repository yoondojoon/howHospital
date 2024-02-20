package kr.or.iei.member.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberRowMapper implements RowMapper<Member>{
	
	
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		
		Member member = new Member();
		
		member.setMemberAddress(rs.getString("member_address"));
		member.setEnrollDate(rs.getString("enroll_date"));
		member.setMemberEmail(rs.getString("member_email"));
		member.setMemberName(rs.getString("member_name"));
		member.setMemberNo(rs.getInt("member_no"));
		member.setMemberPassword(rs.getString("member_password"));
		member.setMemberPhone(rs.getString("member_phone"));
		member.setMemberRrn(rs.getInt("member_rrn"));
		member.setMemberStatus(rs.getInt("member_status"));
		member.setMemberType(rs.getInt("member_type"));
		member.setReportCount(rs.getInt("report_count"));
		
		
		
		
		return member;
	}
}
