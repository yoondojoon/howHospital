package kr.or.iei.admin.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class NoticeRowMapper implements RowMapper<Notice> {
	
	public Notice mapRow(ResultSet rs, int rowNum) throws SQLException{
		Notice n = new Notice();
		n.setNoticeNo(rs.getInt("notice_no"));
		n.setMemberNo(rs.getInt("member_no"));
		n.setNoticeTitle(rs.getString("notice_title"));
		n.setReadCount(rs.getInt("read_count"));
		n.setReqDate(rs.getString("req_date"));
		n.setMemberName(rs.getString("member_name"));
		return n;
	}
}
