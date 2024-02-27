package kr.or.iei.admin.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class HospitalReportRowMapper implements RowMapper<HospitalReport> {

	public HospitalReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		HospitalReport hr = new HospitalReport();
		hr.setReportNo(rs.getInt("report_no"));
		hr.setReportTitle(rs.getString("report_title"));
		hr.setReportContent(rs.getString("report_content"));
		hr.setReportStatus(rs.getInt("report_status"));
		hr.setMemberNo(rs.getInt("member_no"));
		hr.setHospitalName(rs.getString("hospital_name"));
		return hr;
	}
}
