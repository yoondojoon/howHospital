package kr.or.iei.community.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CommunityFileRowMapper implements RowMapper<CommunityFile> {

	@Override
	public CommunityFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommunityFile communityFile = new CommunityFile();
		communityFile.setFilename(rs.getString("filename"));
		communityFile.setFileNo(rs.getInt("file_no"));
		communityFile.setFilepath(rs.getString("filepath"));
		communityFile.setBoardNo(rs.getInt("board_no"));
		return communityFile;
	}

}
