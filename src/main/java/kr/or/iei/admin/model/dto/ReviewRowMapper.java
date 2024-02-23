package kr.or.iei.admin.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewRowMapper implements RowMapper<Review> {

	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review r = new Review();
		r.setReviewNo(rs.getInt("review_no"));
		r.setMemberName(rs.getString("member_name"));
		r.setReviewTitle(rs.getString("review_title"));
		r.setReviewContent(rs.getString("review_content"));
		r.setReviewRating(rs.getString("review_rating"));
		r.setReviewDate(rs.getString("review_date"));
		r.setReviewImg(rs.getString("review_img"));
		return r;
	}

}
