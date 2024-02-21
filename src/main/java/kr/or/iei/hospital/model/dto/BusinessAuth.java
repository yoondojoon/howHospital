package kr.or.iei.hospital.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessAuth {
	private int businessAuthNo; //seq 채번
	private int memberNo;		//businessauth hidden value
	private int representativeNo; 
	private String regDate;		//sysdate
	private List<BusinessAuthFile> fileList; //service:controller 데이터 수신
}
