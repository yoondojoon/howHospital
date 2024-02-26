package kr.or.iei.reservation.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationFileRowMapper implements RowMapper<ReservationFile> {

	@Override
	public ReservationFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReservationFile file = new ReservationFile();
		file.setFileNo(rs.getInt("file_no"));
		file.setReservationNo(rs.getInt("reservation_no"));
		file.setFilename(rs.getString("filename"));
		file.setFilepath(rs.getString("filepath"));
		return file;
	}
	
}
