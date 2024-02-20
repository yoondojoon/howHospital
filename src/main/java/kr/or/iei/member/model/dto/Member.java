package kr.or.iei.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
	
	private int memberNo;
	private int memberType;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private String memberPhone;
	private int memberRrn;
	private String memberAddress;
	private String enrollDate;
	private int memberStatus;
	private int reportCount;
	
}
