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
		
		myReview.setMemberNo(rs.getInt("member_no"));
		myReview.setReservationNo(rs.getInt("reservation_no"));
		myReview.setReviewContent(rs.getString("review_content"));
		myReview.setReviewDate(rs.getString("review_date"));
		myReview.setReviewImg(rs.getString("review_img"));
		myReview.setReviewNo(rs.getInt("review_no"));
		myReview.setReviewRating(rs.getInt("review_rating"));
		myReview.setReviewTitle(rs.getString("review_title"));
		myReview.setHospitalNo(rs.getInt("hospital_no"));
		
		return myReview;
		
	}
	
}
