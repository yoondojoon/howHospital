package kr.or.iei.admin.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper; // 수정 필요

import org.springframework.stereotype.Component;

@Component
public class MemberReportRowMapper implements RowMapper<MemberReport> {

    public MemberReport mapRow(ResultSet rs, int rowNum) throws SQLException {
        MemberReport mr = new MemberReport();
        mr.setBoardNo(rs.getInt("board_no"));
        mr.setMemberNo(rs.getInt("member_no"));
        mr.setReportContent(rs.getString("report_content"));
        mr.setReportTitle(rs.getString("report_title"));
        mr.setReportNo(rs.getInt("report_no"));
        mr.setReportStatus(rs.getInt("report_status"));
        return mr;
    }
}