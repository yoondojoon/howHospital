package kr.or.iei.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyReview {
	private int reviewNo;
	private int hopitalNo;
	private String memberName;
	private int reservationNo;
	private int memberNo;
	private int hospitalNo;
	private String reviewTitle;
	private String reviewContent;
	private int reviewRating;
	private String reviewDate;
	private String reviewImg;
	private String memberName;
	private String reservation_time;
	

}
