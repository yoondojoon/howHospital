package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class KeywordRowMapper implements RowMapper<Keyword> {

	@Override
	public Keyword mapRow(ResultSet rs, int rowNum) throws SQLException {
		Keyword keyword = new Keyword();
		keyword.setKeyword(rs.getString("keyword"));
		keyword.setSubjectNo(rs.getInt("subject_no"));
		return keyword;
	}

}
