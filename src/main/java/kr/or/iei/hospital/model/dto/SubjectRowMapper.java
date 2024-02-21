package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SubjectRowMapper implements RowMapper<Subject> {

	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
		Subject subject = new Subject();
		subject.setSubjectName(rs.getString("subject_name"));
		subject.setSubjectNo(rs.getInt("subject_no"));
		return subject;
	}

}
