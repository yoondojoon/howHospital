package kr.or.iei.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Faq {
	private int faqNo;
	private int memberNo;
	private String faqTitle;
	private String faqContent;
	private int faqRef;
	private int category;
}
