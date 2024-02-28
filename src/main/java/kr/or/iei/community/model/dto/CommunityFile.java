package kr.or.iei.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityFile {
	private int fileNo;
	private int boardNo;
	private String filename;
	private String filepath;
}
