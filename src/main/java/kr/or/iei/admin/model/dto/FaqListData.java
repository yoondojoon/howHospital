package kr.or.iei.admin.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FaqListData {
	private Faq faq;
	private List categoryList;
	private List contentList;
	
}
