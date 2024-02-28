package kr.or.iei.hospital.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HospitalMemberReportListData {
	private List list;
	private String pageNavi;
}
