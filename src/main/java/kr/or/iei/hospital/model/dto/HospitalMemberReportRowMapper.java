package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class HospitalMemberReportRowMapper implements RowMapper<HospitalMemberReport> {
	
	@Override
	public HospitalMemberReport mapRow(ResultSet rs, int rowNum) throws SQLException{
		HospitalMemberReport hmr = new HospitalMemberReport();
		hmr.setMemberName(rs.getString("member_name"));
		hmr.setMemberNo(rs.getInt("member_no"));
		hmr.setRepoDate(rs.getString("repo_date"));
		hmr.setRepoNo(rs.getInt("repo_no"));
		hmr.setReportReason(rs.getString("report_reason"));
		hmr.setReviewNo(rs.getInt("review_no"));
		hmr.setReviewReportStatus(rs.getInt("review_report_status"));
		return hmr;
		
	}
}
