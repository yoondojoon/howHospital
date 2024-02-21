package kr.or.iei.hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessAuthFile {
	private int fileNo;
	private int businessAuthNo;
	private String filename;
	private String filepath; //다운로드 안받으면 이것만 있으면 됨.
}
