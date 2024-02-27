package kr.or.iei.reservation.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationFileDataRowMapper implements RowMapper<ReservationFileData>{

	@Override
	public ReservationFileData mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReservationFileData rfd = new ReservationFileData();
		rfd.setFilepath(rs.getString("filepath"));
		rfd.setReservationNo(rs.getInt("reservation_no"));
		return rfd;
	}

}
