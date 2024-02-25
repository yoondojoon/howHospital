package kr.or.iei.admin.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class FaqRowMapper implements RowMapper<Faq> {
	
	public Faq mapRow(ResultSet rs, int rowNum) throws SQLException{
		Faq f = new Faq();
		f.setFaqNo(rs.getInt("faq_no"));
		f.setMemberNo(rs.getInt("member_no"));
		f.setFaqTitle(rs.getString("faq_title"));
		f.setFaqContent(rs.getString("faq_content"));
		f.setFaqRef(rs.getInt("faq_ref"));
		f.setCategory(rs.getString("category"));
		return f;
	}
	
}
