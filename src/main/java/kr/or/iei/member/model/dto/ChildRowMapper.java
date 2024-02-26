package kr.or.iei.member.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ChildRowMapper implements RowMapper<Child>{

	@Override
	public Child mapRow(ResultSet rs, int rowNum) throws SQLException {
		Child c = new Child();
		c.setChildNo(rs.getInt("child_no"));
		c.setMemberNo(rs.getInt("member_no"));
		c.setChildName(rs.getString("child_name"));
		c.setChildRrn(rs.getInt("child_rrn"));
		return c;
	}
	
}
