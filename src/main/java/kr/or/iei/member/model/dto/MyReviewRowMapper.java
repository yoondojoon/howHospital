package kr.or.iei.member.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MyReviewRowMapper implements RowMapper<MyReview>{
	
	@Override
	public MyReview mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		
		MyReview myReview = new MyReview();
		myReview.setReviewNo(rs.getInt("review_no"));
		myReview.setReservationNo(rs.getInt("reservation_no"));
		myReview.setMemberNo(rs.getInt("member_no"));
		myReview.setReviewTitle(rs.getString("review_title"));
		myReview.setReviewContent(rs.getString("review_content"));
		myReview.setReviewRating(rs.getInt("review_rating"));
		myReview.setReviewDate(rs.getString("review_date"));
		myReview.setReviewImg(rs.getString("review_img"));
		myReview.setHopitalNo(rs.getInt("hospital_no"));
		return myReview;
		
	}
	
}
