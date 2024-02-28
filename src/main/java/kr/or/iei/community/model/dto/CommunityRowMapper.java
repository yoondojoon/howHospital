package kr.or.iei.community.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CommunityRowMapper implements RowMapper<Community>{

	@Override
	public Community mapRow(ResultSet rs, int rowNum) throws SQLException {
		Community c = new Community();
		c.setBoardNo(rs.getInt("board_no"));
		c.setBoardTitle(rs.getString("board_title"));
		c.setBoardContent(rs.getString("board_content"));
		c.setReadCount(rs.getInt("read_count"));
		c.setBoardWriter(rs.getString("board_writer"));
		c.setWriteDate(rs.getString("write_date"));
		return c;
	}
	
}
