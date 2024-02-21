package kr.or.iei.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessAuth {
	private int representativeNo;
	private int memberNo;
	private String businessCertificate;
	private String doctorCertificate;
}
