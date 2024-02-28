package kr.or.iei.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor	
@Data
public class Review {
	private int reviewNo;
	private int reservationNo;
	private int memberNo;
	private int hospitalNo;
	private String reviewTitle;
	private String reviewContent;
	private String reviewRating;
	private String reviewDate;
	private String reviewImg;
	private String memberName;
}
