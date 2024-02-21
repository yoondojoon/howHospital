package kr.or.iei.hospital.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorRowMapper implements RowMapper<Doctor>{

	@Override
	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor d = new Doctor();
		d.setDoctorEducation(rs.getString("doctor_education"));
		d.setDoctorExperience(rs.getString("doctor_experience"));
		d.setDoctorName(rs.getString("doctor_name"));
		d.setDoctorNo(rs.getInt("doctor_no"));
		d.setDoctorPicture(rs.getString("doctor_picture"));
		d.setHospitalNo(rs.getInt("hospital_no"));
		d.setSubjectNo(rs.getInt("subject_no"));
		return d;
	}

}
