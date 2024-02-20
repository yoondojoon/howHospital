package kr.or.iei.test.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SubjectRowMapper implements RowMapper<Subject>{

	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
		Subject s = new Subject();
		s.setSubjectName(rs.getString("subject_name"));
		s.setSubjectNo(rs.getInt("subject_no"));
		return s;
	}
	
}
