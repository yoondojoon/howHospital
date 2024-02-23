package kr.or.iei.admin.model.dto;

import java.util.List;

import kr.or.iei.hospital.model.dto.BusinessAuthFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminBusinessAuth {
	private String memberName;
	private int businessAuthNo;
	private String memberEmail;
	private String memberPhone;
	private List<BusinessAuthFile> fileList;
	private String representativeNo;
	private String regDate;
}
