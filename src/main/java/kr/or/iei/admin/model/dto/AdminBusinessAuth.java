package kr.or.iei.admin.model.dto;

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
	private String fileName;
	private String filePath;
	private int representativeNo;
	private String regDate;
}
