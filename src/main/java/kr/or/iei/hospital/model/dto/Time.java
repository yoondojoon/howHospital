package kr.or.iei.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Time {
	private int hospitalNo;
	private String dayHour;
	private String weekendHour;
	private String lunchHour;
	private int holiday;
}
