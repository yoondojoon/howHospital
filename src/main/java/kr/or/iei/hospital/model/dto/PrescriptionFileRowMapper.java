package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionFileRowMapper implements RowMapper<PrescriptionFile>{

	@Override
	public PrescriptionFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		PrescriptionFile pf = new PrescriptionFile();
		pf.setPrescriptionDate(rs.getString("prescription_date"));
		pf.setPrescriptionName(rs.getString("prescription_name"));
		pf.setPrescriptionNo(rs.getInt("prescription_no"));
		pf.setPrescriptionPath(rs.getString("prescription_path"));
		pf.setReservationNo(rs.getInt("reservation_no"));
		return pf;
	}

}
