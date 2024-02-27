package kr.or.iei.reservation.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationDetailListRowMapper implements RowMapper<ReservationDetailList>{

	@Override
	public ReservationDetailList mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReservationDetailList rdl = new ReservationDetailList();
		rdl.setFilename(rs.getString("filename"));
		rdl.setFilepath(rs.getString("filepath"));
		rdl.setMemberName(rs.getString("member_name"));
		rdl.setReservationStatus(rs.getInt("reservation_status"));
		rdl.setReservationTime(rs.getString("reservation_time"));
		rdl.setReservationType(rs.getInt("reservation_type"));
		rdl.setSymptom(rs.getString("symptom"));
		rdl.setReservationNo(rs.getInt("reservation_no"));
		rdl.setHospitalNo(rs.getInt("hospital_no"));
		rdl.setPrescriptionStatus(rs.getInt("prescription_status"));
		return rdl;
	}

}
