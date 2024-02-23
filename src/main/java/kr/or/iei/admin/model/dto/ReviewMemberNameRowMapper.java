package kr.or.iei.admin.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewMemberNameRowMapper implements RowMapper<Review> {

	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review r = new Review();
		r.setMemberNo(rs.getInt("member_no"));
		r.setReservationNo(rs.getInt("reservation_no"));
		r.setReviewContent(rs.getString("review_content"));
		r.setReviewDate(rs.getString("review_date"));
		r.setReviewImg(rs.getString("review_img"));
		r.setReviewNo(rs.getInt("review_no"));
		r.setReviewRating(rs.getString("review_rating"));
		r.setReviewTitle(rs.getString("review_title"));
		r.setMemberName(rs.getString("member_name"));
		return r;
	}

}
