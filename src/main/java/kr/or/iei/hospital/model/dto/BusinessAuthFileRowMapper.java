package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BusinessAuthFileRowMapper implements RowMapper<BusinessAuthFile>{

	@Override
	public BusinessAuthFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		BusinessAuthFile businessAuthFile = new BusinessAuthFile();
		businessAuthFile .setFileNo(rs.getInt("file_no"));
		businessAuthFile .setBusinessAuthNo(rs.getInt("businessauth_no"));
		businessAuthFile .setFilename(rs.getString("filename"));
		businessAuthFile .setFilepath(rs.getString("filepath"));
		return businessAuthFile;
	}

	
	
}
