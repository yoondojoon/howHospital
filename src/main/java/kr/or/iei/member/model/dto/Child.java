package kr.or.iei.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Child {
	private int childNo;
	private int memberNo;
	private String childName;
	private int childRrn;
}
