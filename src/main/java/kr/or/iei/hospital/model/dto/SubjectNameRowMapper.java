package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SubjectNameRowMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
		Subject s = new Subject();
		s.setSubjectName(rs.getString("subject_name"));
		return s;
	}

}
