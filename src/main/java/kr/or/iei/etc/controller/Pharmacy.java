package kr.or.iei.etc.controller;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pharmacy {
	private String dutyName;
	private String dutyAddr;
	private String dutyTel1;
	private String lat;
	private String lng;
}
