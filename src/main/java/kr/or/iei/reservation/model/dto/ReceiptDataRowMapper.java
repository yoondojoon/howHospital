package kr.or.iei.reservation.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ReceiptDataRowMapper implements RowMapper<ReceiptData>{

	@Override
	public ReceiptData mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReceiptData rd = new ReceiptData();
		rd.setDoctorName(rs.getString("doctor_name"));
		rd.setDoctorNo(rs.getInt("doctor_no"));
		rd.setHospitalNo(rs.getInt("hospital_no"));
		rd.setMemberAddress(rs.getString("member_address"));
		rd.setMemberName(rs.getString("member_name"));
		rd.setMemberNo(rs.getInt("member_no"));
		rd.setRegReservation(rs.getString("reg_reservation"));
		rd.setHospitalName(rs.getString("hospital_name"));
		rd.setCostOne(rs.getString("cost_one"));
		rd.setMemberPhone(rs.getString("member_phone"));
		rd.setReservationNo(rs.getInt("reservation_no"));
		return rd;
	}

}
