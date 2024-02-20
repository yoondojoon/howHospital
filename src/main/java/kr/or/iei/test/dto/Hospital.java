package kr.or.iei.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hospital {
	private int hospitalNo;
	private int memberNo;
	private String hospitalName;
	private String hospitalIntro;
	private String hospitalAddress;
	private String lat;
	private String lng;
	private String hospitalTel;
	private String costOne;
	private String costTwo;
}
